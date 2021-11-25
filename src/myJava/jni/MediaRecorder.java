package myJava.jni;

public class MediaRecorder {
    static {
        System.loadLibrary("media_jni");
        native_init();
    }
    private static native final void native_init();
    public native void start() throws IllegalStateException;

    private native final void native_setup(Object mediarecorder_this,
                                           String clientName, String opPackageName) throws IllegalStateException;
}
