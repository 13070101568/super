$(document).ready(function() {
	    
	//ȫѡ����ѡ
	$("#selAll").click(function(){
    	$(":checkbox").not("#selAll").each(function(){
    		$(this).prop("checked",!$(this).prop("checked"));
    	});
    });
    
	// �������ĸ�ѡ��ȡ��ȫѡ
	$(":checkbox").not("#selAll").click(function() {
		var falg = true;
		$(":checkbox").not("#selAll").each(function() {
			if (!$(this).prop("checked")) {
				falg = false;
				return;
			}
		});
		$("#selAll").prop("checked", falg);
	});
	
	//ȡ����ѡ
	$("#resetAll").click(function(){
		$(":checkbox").prop("checked",false);
	});

});