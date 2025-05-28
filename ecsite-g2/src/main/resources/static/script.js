


$(document).ready(function () {
  $('.sidebar_btn').on('click', function () {
    toggleSidebar();
  });
  
  $(function() {
    let lastCurrentBtn = null;

    $('.sidebar button').hover(
      function() {
        // 鼠标进入
        if ($(this).hasClass('current')) {
          // 自己已经是current，直接return
          lastCurrentBtn = null;
          return;
        } else {
			// 找到当前有current的按钮
	        lastCurrentBtn = $('.sidebar button.current').first();
	        if (lastCurrentBtn.length) {
	          lastCurrentBtn.removeClass('current');
	        } else {
	          lastCurrentBtn = null;
	        }
	        // 给悬浮按钮加current
	        $(this).addClass('current');
		}
      },
      function() {
        // 鼠标移出
		if (!lastCurrentBtn) {
          // 自己已经是current，直接return
          return;
        } else {
			$(this).removeClass('current');
			lastCurrentBtn.addClass('current');
			lastCurrentBtn = null;
		}
      }
    );
  });
});


function toggleSidebar() {
  $('#sidebar').toggleClass('active');
  $('#overlay').toggleClass('active');
}


$(document).on("input", ".check", function() {
    const $input = $(this);
    const field = $input.data("name");
    if (!field) return;

    const val = $input.val().trim();

    let $warning = $input.parent().find(".input-warning");
    if (!$warning.length) {
        $warning = $('<div class="input-warning"></div>');
        $input.parent().append($warning);
    }

    let [isValid, errMsg] = check(field, val);

    if (!isValid) {
        $warning.text(errMsg).addClass("show");
        $input.css({
            "background-color": "#ffe5e5",
            "transition": "background-color 0.3s ease"
        });
    } else {
        $input.css("background-color", "");
        $warning.removeClass("show");
    }
	
	toggleSubmitButton();
});

function check(field, str){
    let b, errMsg;

    switch(field) {
        case "nickName":
		case "lastName1":
		case "firstName1":
		case "lastName2":
		case "firstName2":
            b = (str.length >= 1 && str.length <= 10);
            errMsg = b ? "" : "1〜10文字以内にしてください";
            break;
			
			case "address1":
			case "address2":
			case "address3":
	            b = (str.length >= 1);
	            errMsg = b ? "" : "入力してください";
	            break;

        case "postNumber1":
            b = /^\d{3}$/.test(str);
            errMsg = b ? "" : "3桁の数字を入力してください";
            break;

        case "postNumber2":
            b = /^\d{4}$/.test(str);
            errMsg = b ? "" : "4桁の数字を入力してください";
            break;

        case "birthday":
            b = (str.length > 0) && isValidDate(str);
            errMsg = b ? "" : "正しい日付を入力してください（yyyy-mm-dd）";
            break;

        case "creditNumber1":
        case "creditNumber2":
        case "creditNumber3":
        case "creditNumber4":
            b = /^\d{4}$/.test(str);
            errMsg = b ? "" : "4桁の数字を入力してください";
            break;

        default:
            b = true;
            errMsg = "";
    }

    return [b, errMsg];
}

// 控制提交按钮是否禁用
function toggleSubmitButton() {
    let allValid = true;
    $(".check").each(function () {
        const $input = $(this);
        const field = $input.data("name");
        if (!field) return;

        const val = $input.val().trim();
        const [isValid] = check(field, val);
        if (!isValid) {
            allValid = false;
            return false; // break out of .each
        }
    });

    $("#submit").prop("disabled", !allValid);
}
