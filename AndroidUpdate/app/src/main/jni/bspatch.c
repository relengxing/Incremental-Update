#include <jni.h>

JNIEXPORT void JNICALL
Java_com_relengxing_androidupdate_BsPatch_patch(JNIEnv *env, jclass type, jstring oldFile_,
                                                jstring newFile_, jstring patchFile_) {
    const char *oldFile = (*env)->GetStringUTFChars(env, oldFile_, 0);
    const char *newFile = (*env)->GetStringUTFChars(env, newFile_, 0);
    const char *patchFile = (*env)->GetStringUTFChars(env, patchFile_, 0);

    // TODO

    (*env)->ReleaseStringUTFChars(env, oldFile_, oldFile);
    (*env)->ReleaseStringUTFChars(env, newFile_, newFile);
    (*env)->ReleaseStringUTFChars(env, patchFile_, patchFile);
}