import de.hybris.platform.spring.HybrisContextLoaderListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

def f = ContextLoader.getDeclaredField("currentContextPerThread");
f.setAccessible(true);
appContext = null;
Map<ClassLoader, WebApplicationContext> contexts = f.get(HybrisContextLoaderListener);
for (loader in contexts) {
    contextName = loader.getKey().getContextName();
    appContext = loader.getValue();
// print any of context attributes (appContext)
    println contextName + "\t" + appContext.getId();

}