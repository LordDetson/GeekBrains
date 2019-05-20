@Service(name = "myLazyService", lazyLoad = true)
public class LazyService {
    @Init
    public void lazyService() throws Exception {
        System.out.println(this.getClass().getName() + " - lazyService()");
    }

    public void foo() {
        System.out.println(this.getClass().getName() + " - foo()");
    }
}
