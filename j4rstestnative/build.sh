#!/bin/bash
rustdir="$(dirname "$0")"
cd "$rustdir"

cargo build --target=aarch64-linux-android
cargo build

mkdir -p ../app/src/main/jniLibs/{arm64-v8a,armeabi,armeabi-v7a,x86_64}

cp ./target/aarch64-linux-android/debug/libj4rstest.so ../app/src/main/jniLibs/arm64-v8a
cp ./target/debug/libj4rstest.so ../app/src/main/jniLibs/x86_64