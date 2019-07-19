package jvm.zhou_zhi_ming.c07.test3.demo;

public class ClassLoad {
     ClassLoad parent;

     public ClassLoad(){
         this(new ApplicationLoad());
     }

    public ClassLoad(ClassLoad classLoad){
        //类加载器默认为启动类加载器
         parent=classLoad;
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        Class cls = null;
        System.out.println("begin---"+this.getClass().getSimpleName());
        if (parent != null) {
            cls = parent.loadClass(name);
        } else {
            cls = findBootstrapClassOrNull(name);
        }
        System.out.println("end---"+this.getClass().getSimpleName());

        if (cls == null) {
            cls=findClass(name);
        }

        return cls;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }

    protected Class defineClass(byte[] bytes){
        return new Class( bytes);
    }


    public Class findBootstrapClassOrNull(String name) {
        //调用启动类加载器加载，加载不成功返回null
        Class cls = new BootstrapLoad().nativeFindClass(name);
        return cls;
    }
}
