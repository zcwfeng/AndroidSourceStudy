	第一种方式启动
	
	<script type="text/javascript">
		alert("启动APK");
		window.location="testjs://包名";-》com.zcwfeng.sourcestudy
		</script>
	
	第二种方式启动
	
	<script type="text/javascript">
		
		//ios判断  if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) 
		
		//判断是否是android设备浏览器	if (navigator.userAgent.match(/android/i)) {
	if (navigator.userAgent.match(/android/i)) {

		document.getElementById('openApp').onclick = function(e) {
			// 通过iframe的方式试图打开APP，如果能正常打开，会直接切换到APP，并自动阻止a标签的默认行为
			// 否则打开a标签的href链接
			var ifrSrc = 'testjs://platformapi/startApp?name=tom&age=18';
			if (!ifrSrc) {
				return;
			}
			var ifr = document.createElement('iframe');
			ifr.src = ifrSrc;
			ifr.style.display = 'none';
			document.body.appendChild(ifr);
			setTimeout(function() {
				document.body.removeChild(ifr);

			}, 1000);

		};
		if (document.all) {
			document.getElementById('openApp').click();
		}
		// 其它浏览器
		else {
			var e = document.createEvent("MouseEvents");
			e.initEvent("click", true, true);
			document.getElementById("openApp").dispatchEvent(e);
		}
	}
	
	
	//参考其它人用window.location 或 window.open标签也可以启动本地APK，但没有处理url跳转不存在的问题。
	//（window.open未测试成功）
	
android客户端的配置，在mainifest里面加入action的配置schema
    注意 host标签
<intent-filter>
    <action android:name="android.intent.action.VIEW" /> 
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" /> 
    <data android:scheme="testjs" android:host="com.zcwfeng.sourcestudy"/>  
</intent-filter>