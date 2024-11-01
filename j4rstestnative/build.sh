#!/bin/bash
rustdir="$(dirname "$0")"
cd "$rustdir"

cargo build --target=armv7-linux-androideabi
cargo build --target=aarch64-linux-android
cargo build --target=i686-unknown-linux-gnu
cargo build

mkdir -p ../app/src/main/jniLibs/{arm64-v8a,armeabi,armeabi-v7a,x86_64}

cp ./target/armv7-linux-androideabi/debug/libj4rstest.so ../app/src/main/jniLibs/armeabi-v7a
cp ./target/aarch64-linux-android/debug/libj4rstest.so ../app/src/main/jniLibs/arm64-v8a
cp ./target/i686-unknown-linux-gnu/debug/libj4rstest.so ../app/src/main/jniLibs/x86
cp ./target/debug/libj4rstest.so ../app/src/main/jniLibs/x86_64