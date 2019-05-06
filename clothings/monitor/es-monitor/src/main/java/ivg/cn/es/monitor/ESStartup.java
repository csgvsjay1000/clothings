package ivg.cn.es.monitor;

/**
 * Hello world!
 *
 */
public class ESStartup 
{
    public static void main( String[] args )
    {
    	ESController controller = new ESController();
    	controller.startup();
        System.out.println( "Hello World!" );
    }
}
