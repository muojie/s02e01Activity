//
// Created by Administrator on 2015/9/25.
//
#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_example_administrator_s02e01activity_dbtester_DBTesterActivity_getNativeMessage
        (JNIEnv *env, jobject thisObj) {
    return (*env)->NewStringUTF(env, "Hello from native code!");
}