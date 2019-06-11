
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
            if (export == null) {
                export = provider.getExport();
            }
            if (delay == null) {
                delay = provider.getDelay();
            }
        }
        if (export != null && !export) {
            return;
        }

        if (delay != null && delay > 0) {
            delayExportExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    doExport();
                }
            }, delay, TimeUnit.MILLISECONDS);
        } else {
            doExport();
        }
    }

```