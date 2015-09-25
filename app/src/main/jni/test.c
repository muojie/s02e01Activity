//
// Created by Administrator on 2015/9/25.
//
#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_example_administrator_s02e01activity_dbtester_DBTesterActivity_getNativeMessage
        (JNIEnv *env, jobject thisObj) {

#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
      #if defined(__ARM_NEON__)
        #if defined(__ARM_PCS_VFP)
          #define ABI "armeabi-v7a/NEON (hard-float)"
        #else
          #define ABI "armeabi-v7a/NEON"
        #endif
      #else
        #if defined(__ARM_PCS_VFP)
          #define ABI "armeabi-v7a (hard-float)"
        #else
          #define ABI "armeabi-v7a"
        #endif
      #endif
    #else
     #define ABI "armeabi"
    #endif
#elif defined(__i386__)
    #define ABI "x86"
#elif defined(__x86_64__)
    #define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
    #define ABI "mips64"
#elif defined(__mips__)
    #define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
    #define ABI "unknown"
#endif
    return (*env)->NewStringUTF(env, "Hello from native code!  Compiled with ABI " ABI ".");
}