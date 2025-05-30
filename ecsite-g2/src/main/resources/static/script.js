
$(document).ready(function () {
    // 检查页面上是否有任意一个目标元素
    if ($("#info_check").length === 0 &&
        $("#info_edit").length === 0 &&
		$("#top").length === 0 &&
        $("#user_admin").length === 0) {
        return; // 什么都没有就不生成
    }

    const $sidebar = $('<div>', { class: 'sidebar', id: 'sidebar' });
	
	// 情報確認
	    if ($("#top").length > 0) {
	        $sidebar.append('<button class="current">トップページ</button>');
	    } else {
	        $sidebar.append(`
	            <form method="get" action="/items">
	                <button type="submit">トップページ</button>
	            </form>
	        `);
	    }

    // 情報確認
    if ($("#info_check").length > 0) {
        $sidebar.append('<button class="current">情報確認</button>');
    } else {
        $sidebar.append(`
            <form method="get" action="/mypage">
                <button type="submit">情報確認</button>
            </form>
        `);
    }

    // 情報編集
    if ($("#info_edit").length > 0) {
        $sidebar.append('<button class="current">情報編集</button>');
    } else {
        $sidebar.append(`
            <form method="post" action="/mypage/edit">
                <button type="submit">情報編集</button>
            </form>
        `);
    }

    // 管理者チェック（ThymeleafからisAdminをdata属性で渡しておく）

    if (window.isAdmin) {
        if ($("#user_admin").length > 0) {
            $sidebar.append('<button class="current">顧客一覧</button>');
        } else {
            $sidebar.append(`
                <form method="get" action="/manager/customerlist">
                    <button type="submit">顧客一覧</button>
                </form>
            `);
        }
    }

    // 插入 Sidebar（你可以根据实际情况插入到指定位置）
    $("body").prepend($sidebar);
});

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
		$input.parent().css("position", "relative");
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
			
		case "email":
            b = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(str);
            errMsg = b ? "" : "正しいメールアドレスを入力してください";
            break;

        case "phone_number":
            b = /^\d{10,11}$/.test(str); // 例：09012345678 或 0312345678
            errMsg = b ? "" : "10〜11桁の数字を入力してください";
            break;
			
		case "password":
            b = str.length > 0;
            errMsg = b ? "" : "パスワードを入力してください";
            break;

        case "confirmPassword":
            const password = $("input[data-name='password']").val();
            b = str.length > 0 && str === password;
            if (str.length === 0) {
                errMsg = "確認用パスワードを入力してください";
            } else if (str !== password) {
                errMsg = "パスワードが一致しません";
            } else {
                errMsg = "";
            }
            break;

        default:
            b = true;
            errMsg = "";
    }

    return [b, errMsg];
}


