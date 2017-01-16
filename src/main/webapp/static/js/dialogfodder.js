;(function(){
    var PopFodder = function(config){
        var _this_ = this;
        //默认配置
        this.config = {
            width:"706px",
            height:"232px",
            header:"",
            content:"",
            buttons:null,
            footCustom:null,
        };
        //默认参数扩展
        if(config && $.isPlainObject(config)){
            $.extend(this.config,config)
        }
        this.body = $("body");
        this.mask = $('<div class="fodderMask"></div>');
        this.win = $('<div class="fodderWin"></div>');
        this.title = $('<div class="fodderTitle"></div>');
        this.titleText = $('<span class="fodderText"></span>');
        this.closed = $('<span class="fodderClose">×</span>');
        this.content = $('<div class="fodderContent"></div>');
        this.footer = $('<div class="fodderFooter"></div>');
        this.nav = $('<div class="fodderNav"></div>');
        this.navBtn = $('<div class="navBtn"><div class="selAll">全选</div></div>');
        this.create();        
    };
    PopFodder.prototype = {
        create:function(){
            var _this_ = this;
            var config = this.config,
            body = this.body,
            mask = this.mask,
            win = this.win,
            title = this.title,
            titleText = this.titleText,
            closed = this.closed,
            content = this.content,
            footer = this.footer;
            nav=this.nav;
            navBtn=this.navBtn;
            //关闭事件
            closed.click(function(){
                _this_.close();
            })
            //添加title
            if(config.header != "" && typeof config.header != "string"){
                title.append(titleText.append(config.header));
                title.append(closed);
                win.append(title);
            }else{
                title.append(titleText.html(config.header));
                title.append(closed);
                win.append(title);
            }
            //添加nav
            nav.append(navBtn)
            win.append(nav);
            
            //添加内容
            content.css("height",config.height);
            if(config.content != "" &&typeof config.content != "string"){
                win.append(content.append(config.content));
            }else{
                win.append(content.html(config.content));
            }
            //添加底部
            if(config.footCustom != null){                
                footer.append(footCustom);
            }else if(config.buttons != null){
                $(config.buttons).each(function(){
                    var type = this.type?this.type:"";
                    var btnText = this.text;
                    var callback = this.callback?this.callback:null; 
                    var button = $("<span class='"+type+"'>"+btnText+"</span>");
                    if(callback){
                        button.click(function(){
                            callback();
                        });
                    }else{
                        button.click(function(){
                            _this_.close();
                        })
                    }
                    footer.append(button)
                }); 
            }
            win.append(footer);
            if(config.width != "300px"){
                mask.append(win.width(config.width));
            }else{
                mask.append(win);
            }
            mask.css("height",$(document).height());
            body.append(mask);
        },
        close:function(){
            this.mask.remove();
        }
    }
    window.PopFodder = PopFodder;
})(jQuery);
$(document).on("click",".fodderMask",function(e){
	if(e.target.className == "fodderMask"){
		$(this).remove();
	}	
})
$(document).on("click",".fodderWin",function(e){
})