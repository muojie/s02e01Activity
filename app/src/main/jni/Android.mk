LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    :=  DB_TESTER
LOCAL_SRC_FILES :=  test.cpp

include $(BUILD_SHARED_LIBRARY)