;(function(){
    var Pop = function(config){
        var _this_ = this;
        //默认配置
        this.config = {
            width:"300px",
            header:"",
            content:"",
            buttons:null,
            footCustom:null
        };
        //默认参数扩展
        if(config && $.isPlainObject(config)){
            $.extend(this.config,config)
        }
        this.body = $("body");
        this.mask = $('<div class="popMask"></div>');
        this.win = $('<div class="popWin"></div>');
        this.title = $('<div class="popTitle"></div>');
        this.titleText = $('<span class="popText"></span>');
        this.closed = $('<span class="popClose">×</span>');
        this.content = $('<div class="popContent"></div>');
        this.footer = $('<div class="popFooter"></div>');
        this.create();        
    };
    Pop.prototype = {
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
            
            //添加内容
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
    window.Pop = Pop;
})(jQuery);