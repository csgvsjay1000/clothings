
# Dubbo源码导读03-服务导出

## 1. 简介

本篇文章，我们来研究一下Dubbo服务导出过程。Dubbo 服务导出过程始于 Spring 容器发布刷新事件，Dubbo 在接收到刷新事件后，立即执行服务导出逻辑。整个逻辑分为三部分：

- 第一部分：前置工作，主要检查参数、组装URL。
- 第二部分：导出服务，包含导出服务到本地(JVM)，和导出服务到远程两个过程。
- 第三部分：向注册中心注册服务，用于服务发现。

本章将会对三个部分代码进行详细分析。

## 2. 源码分析

服务导出的入口方法是 ServiceBean 的 onApplicationEvent, onApplicationEvent是事件响应方法，该方法会收到 Spring 上下文刷新事件后，执行服务导出逻辑。方法代码如下:

```java

	@Override 
	public void onApplicationEvent(ContextRefreshedEvent event) {
	    if (isDelay() && !isExported() && !isUnexported()) {
	        if (logger.isInfoEnabled()) {
	            logger.info("The service ready on spring started. service: " + getInterface());
	        }
	        export();
	    }
	}

```

### 2.1 前置工作

前置工作主要包括两部分，分别是配置检查和URL装配。Dubbo 会检查用户的配置是否合理，或为用户补充缺省配置。配置检查完成后，根据这些配置组装URL。接下来我们先分析配置检查部分源码。

```java

	public synchronized void export() {
        if (provider != null) {
			// 获取 export 和 delay 的配置
            if (export == null) {
                export = provider.getExport();
            }
            if (delay == null) {
                delay = provider.getDelay();
            }
        }
        if (export != null && !export) {
			// 如果不导出，则返回
            return;
        }
		
		// 如果 delay > 0 则延时导出服务
        if (delay != null && delay > 0) {
            delayExportExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    doExport();
                }
            }, delay, TimeUnit.MILLISECONDS);
        } else {
			// 立即导出
            doExport();
        }
    }

```

export()方法对两个配置项进行了检查, export和delay。分别是是否导出，和延时导出，并执行不同的动作。下面我们继续分析源码，接下来分析 doExport();

```java

	protected synchronized void doExport() {
        if (unexported) {
            throw new IllegalStateException("Already unexported!");
        }
        if (exported) {
            return;
        }
        exported = true;
        if (interfaceName == null || interfaceName.length() == 0) {
            throw new IllegalStateException("<dubbo:service interface=\"\" /> interface not allow null!");
        }
        checkDefault();
        if (provider != null) {
            if (application == null) {
                application = provider.getApplication();
            }
            if (module == null) {
                module = provider.getModule();
            }
            if (registries == null) {
                registries = provider.getRegistries();
            }
            if (monitor == null) {
                monitor = provider.getMonitor();
            }
            if (protocols == null) {
                protocols = provider.getProtocols();
            }
        }
        if (module != null) {
            if (registries == null) {
                registries = module.getRegistries();
            }
            if (monitor == null) {
                monitor = module.getMonitor();
            }
        }
        if (application != null) {
            if (registries == null) {
                registries = application.getRegistries();
            }
            if (monitor == null) {
                monitor = application.getMonitor();
            }
        }
        if (ref instanceof GenericService) {
            interfaceClass = GenericService.class;
            if (StringUtils.isEmpty(generic)) {
                generic = Boolean.TRUE.toString();
            }
        } else {
            try {
                interfaceClass = Class.forName(interfaceName, true, Thread.currentThread()
                        .getContextClassLoader());
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            checkInterfaceAndMethods(interfaceClass, methods);
            checkRef();
            generic = Boolean.FALSE.toString();
        }
        if (local != null) {
            if ("true".equals(local)) {
                local = interfaceName + "Local";
            }
            Class<?> localClass;
            try {
                localClass = ClassHelper.forNameWithThreadContextClassLoader(local);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            if (!interfaceClass.isAssignableFrom(localClass)) {
                throw new IllegalStateException("The local implementation class " + localClass.getName() + " not implement interface " + interfaceName);
            }
        }
        if (stub != null) {
            if ("true".equals(stub)) {
                stub = interfaceName + "Stub";
            }
            Class<?> stubClass;
            try {
                stubClass = ClassHelper.forNameWithThreadContextClassLoader(stub);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            if (!interfaceClass.isAssignableFrom(stubClass)) {
                throw new IllegalStateException("The stub implementation class " + stubClass.getName() + " not implement interface " + interfaceName);
            }
        }
        checkApplication();
        checkRegistry();
        checkProtocol();
        appendProperties(this);
        checkStubAndMock(interfaceClass);
        if (path == null || path.length() == 0) {
            path = interfaceName;
        }
        doExportUrls();
        ProviderModel providerModel = new ProviderModel(getUniqueServiceName(), this, ref);
        ApplicationModel.initProviderModel(getUniqueServiceName(), providerModel);
    }

```