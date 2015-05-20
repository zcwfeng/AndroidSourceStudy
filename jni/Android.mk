LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_LDLIBS    := -llog 
LOCAL_MODULE    := AndroidSourceStudy
LOCAL_SRC_FILES := AndroidSourceStudy.c

include $(BUILD_SHARED_LIBRARY)
