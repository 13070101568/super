var Common = {
    /**
     * ��ʽ�����ڣ�����ʱ��"00:00:00"��
     */
    formatterDate1: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json��ʽʱ��תjsʱ���ʽ*/
       var date1 = date.time;
        var obj = new Date(date1);
        /*�����ڸ�ʽ��*/
        var datetime = obj.getFullYear()+"/"+obj.getMonth()+"/"+obj.getDate();
        return datetime;
    },
    /**
     * ��ʽ��ȥ���ڣ���ʱ�䣩
     */
    formatterDate2: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json��ʽʱ��תjsʱ���ʽ*/
       // date = date.substr(1, date.length - 2);
        var date1 = date.time;
        var obj = new Date(date1);
      	var datetime = obj.getFullYear()+"/"+obj.getMonth()+"/"+obj.getDate()+" "+obj.getHours()+":"+obj.getMinutes()+":"+obj.getSeconds();
        return datetime;
    }
	};