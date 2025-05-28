



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
