var TimecodeUtil = function(){
	return {
		/**
		 * 时码转为帧
		 * @param {Object} timecodeStr
		 * @return {TypeName} 
		 */
		getFramesFromTimecodeStr:function(timecodeStr){
			var frames = 0;
			var timecodeArr = timecodeStr.split(":");
			var hour = timecodeArr[0];
			var minute = timecodeArr[1];
			var second = timecodeArr[2];
			var frame = timecodeArr[3];
			var totalSecond = parseInt(hour) * 3600 + parseInt(minute) * 60 + parseInt(second);
			frames = totalSecond * 25 + parseInt(frame);
			return frames;
		},
		/**
		 * 帧转化为时码
		 * @param {Object} frameCount
		 * @param {Object} videoStandard
		 * @return {TypeName} 
		 */
		frameToTimeCode:function(frameCount, videoStandard){
			var videoStandard = 1;
			var timeCode = "";
			var sHour = null;
			var sMinute = null;
			var sSecond = null;
			var sFrame = null;
			switch (videoStandard) {
				case 2 :
				case 32 :
				case 2048 :
				case 65536 :
				case 4194304 :
				case 268435456 :
					var hour = Math.floor(frameCount / 107892);
					var dwReste = frameCount % 107892;
					var minute = 10 * Math.floor(dwReste / 17982);
					dwReste %= 17982;
					if (dwReste >= 1800) {
						dwReste -= 1800;
						minute += 1 + Math.floor(dwReste / 1798);
						dwReste %= 1798;
						dwReste += 2;
					}
					sHour = '' + (hour);
					sMinute = '' + (minute);
					sSecond = '' + Math.floor(dwReste / 30);
					sFrame = '' + (dwReste % 30);
					break;
				case 4 :
				case 64 :
				case 131072 :
				case 8388608 :
				case 536870912 :
					var hour = Math.floor(frameCount / 108000);
					var dwReste = frameCount % 108000;
					var minute = Math.floor(dwReste / 1800);
					dwReste %= 1800;

					sHour = '' + (hour);
					sMinute = '' + (minute);
					sSecond = '' + Math.floor(dwReste / 30);
					sFrame = '' + (dwReste % 30);
					break;
				case 1 :
				case 8 :
				case 16 :
				case 1024 :
				case 32768 :
				case 2097152 :
				case 134217728 :
					var hour = Math.floor(frameCount / 90000);
					var dwReste = frameCount % 90000;
					var minute = Math.floor(dwReste / 1500);
					dwReste %= 1500;

					sHour = '' + (hour);
					sMinute = '' + (minute);
					sSecond = '' + Math.floor(dwReste / 25);
					sFrame = '' + (dwReste % 25);
					break;
				case 256 :
				case 512 :
				case 8192 :
				case 16384 :
				case 524288 :
				case 1048576 :
				case 33554432 :
					var hour = Math.floor(frameCount / 86400);
					var dwReste = frameCount % 86400;
					var minute = Math.floor(dwReste / 1440);
					dwReste %= 1440;

					sHour = '' + (hour);
					sMinute = '' + (minute);
					sSecond = '' + Math.floor(dwReste / 24);
					sFrame = '' + (dwReste % 24);
			}

			if (sHour.length < 2)
				sHour = "0" + sHour;
			if (sMinute.length < 2)
				sMinute = "0" + sMinute;
			if (sSecond.length < 2)
				sSecond = "0" + sSecond;
			if (sFrame.length < 2) {
				sFrame = "0" + sFrame;
			}
			timeCode = sHour + ":" + sMinute + ":" + sSecond + ":" + sFrame;
			return timeCode;
		}
	}
}();