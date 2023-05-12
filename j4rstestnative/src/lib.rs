use j4rs::InvocationArg;
use j4rs::jni_sys::{JavaVM, jint, JNI_VERSION_1_6};
use j4rs::prelude::*;
use j4rs_derive::*;
use serde::{Deserialize, Serialize};

#[allow(non_snake_case)]
#[no_mangle]
pub extern fn JNI_OnLoad(env: *mut JavaVM, _reserved: jobject) -> jint {
    j4rs::set_java_vm(env);
    JNI_VERSION_1_6
}

#[call_from_java("com.example.j4rstest.RustFunctionCalls.fnstring")]
fn my_function_with_1_string_arg(i1: Instance) -> Result<Instance, String> {
    let jvm: Jvm = Jvm::attach_thread().unwrap();
    let s: String = jvm.to_rust(i1).unwrap();
    println!("A String Instance was passed to Rust: {}", s);
    let ia = InvocationArg::try_from(format!("Rust received {}", s)).unwrap();
    Instance::try_from(ia).map_err(|error| format!("{}", error))
}

#[call_from_java("com.example.j4rstest.RustFunctionCalls.addintegers")]
fn add_integers(integer_instance1: Instance, integer_instance2: Instance) -> Result<Instance, String> {
    let jvm: Jvm = Jvm::attach_thread().unwrap();
    let i1: i32 = jvm.to_rust(integer_instance1).unwrap();
    let i2: i32 = jvm.to_rust(integer_instance2).unwrap();
    let sum = i1 + i2;
    let ia = InvocationArg::try_from(sum).map_err(|error| format!("{}", error)).unwrap();
    Instance::try_from(ia).map_err(|error| format!("{}", error))
}

#[call_from_java("com.example.j4rstest.RustFunctionCalls.addlongs")]
fn add_longs(instance1: Instance, instance2: Instance) -> Result<Instance, String> {
    let jvm: Jvm = Jvm::attach_thread().unwrap();
    let i1: i64 = jvm.to_rust(instance1).unwrap();
    let i2: i64 = jvm.to_rust(instance2).unwrap();
    let sum = i1 + i2;
    let ia = InvocationArg::try_from(sum).map_err(|error| format!("{}", error)).unwrap();
    Instance::try_from(ia).map_err(|error| format!("{}", error))
}

#[call_from_java("com.example.j4rstest.RustFunctionCalls.fncustomobject")]
fn use_custom_object(i: Instance) -> Result<Instance, String> {
    println!("---------------------IN RUST");
    let jvm: Jvm = Jvm::attach_thread().unwrap();
    let test_dto: TestDto = jvm.to_rust(i).unwrap();
    println!("{:?}", test_dto);
    let test_dto = TestDto {number: 3333};
    let ia = InvocationArg::new(&test_dto, "com.example.j4rstest.api.dto.TestDto");
    Instance::try_from(ia).map_err(|error| format!("{}", error))
}

#[derive(Deserialize, Serialize, Debug)]
struct TestDto {
    number: i32
}