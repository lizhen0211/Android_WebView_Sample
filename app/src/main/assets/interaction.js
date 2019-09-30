function onAndroidCallJsByEvaluate(){
    console.log('onAndroidCallJsByEvaluate has been trigger')
    return 'js_return_val';
}

function onAndroidCallJsByLoadUrl(str){
    console.log('onAndroidCallJsByLoad has been trigger')
    console.log(str)
    alert(str)
    //如果有返回值，webview 页面内容会load成返回值内容
    //return 'js_return_val';
}