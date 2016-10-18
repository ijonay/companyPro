<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div class="newWarnMask">
        <div class="newWarnWarpper">
            <div class="newWarnTitle">
            预警设置
            <span class="newWarnWinClose">
              <img src="img/editifodel.png" />
            </span>
            </div>
            <div class="newWarnSetCon">
                <div id="newWarnFieldChoose">
                    <div class="newWarnFieldTitle"><span class="titleSpan">选择预警字段</span><p class="titleP"></p>
                    </div>
                    <div>
                        <p class="pClass">
                            <span class="marginRight106">预警描述</span><span>预警字段</span>
                        </p>
                        <div class="marginTop8">
                            <input placeholder="请输入预警描述" id="newWarnKeyWords" class="newWarnKeyWords" />
                            <div class="selectWapper marginLeft5  select" id="newWarnFieldSelect">
                                   <p class="selectedDiv" data-value="选择字段">选择字段</p>
                                  
					                <ul class="fieldUl hasField">
					                    
					                </ul>
                            </div>
                            <input id="unit" placeholder="请输入单位" class="newWarnUnit" />
                        </div>
                    </div>
                 </div>
                <div id="newWarnFieldSet">
                    <div class="newWarnFieldSetMargin"><span class="titleSpan">预警字段设置</span><p class="titleP"></p>
                    </div>
                    <div>
                        <p class="pClass">
                            <span class="marginRight106">计算方式</span><span>计算周期</span>
                        </p>
                        <div class="marginTop8">                            
                            <div class="selectWapper select" id="newWarnCountType">
                                 <p class="selectedDiv" data-algorname="求和" data-algor="sum" data-value="求和">求和</p>
                                  
					                <ul>
					                    <li data-value="求和" data-algorname="求和" data-algor="sum" class="Selected">
								            求和
								        </li>
								         <li data-value="计数" data-algorname="计数" data-algor="count">
								            计数
								        </li>
								        <li data-value="最大值" data-algorname="最大值" data-algor="max">
								            最大值
								        </li>
								        <li data-value="最小值" data-algorname="最小值" data-algor="min">
								            最小值
								        </li>
								       
								        <li data-value="平均数" data-algorname="平均数" data-algor="avg">
								            平均数
								        </li>
								        <li data-value="中位数" data-algorname="中位数" data-algor="median">
								            中位数
								        </li>
					                </ul>
                            </div>
                            <div class="selectWapper marginLeft5 select" id="newWarnCountField">
                                 <p class="selectedDiv" data-value="选择周期字段">选择周期字段</p>                                  
					                <ul class="sumdataUl hasField">
					                   
					                </ul>
                            </div>
                            <div class="littleSelectWapper select" id="newWarnCountCycle">
                                <p class="selectedDiv" data-periodEnum="1" data-value="当天">当天</p>
                                  
					                <ul class="ulwidth76">
										<li data-periodEnum="1" data-value="当天">当天</li>
			                            <li data-periodEnum="2" data-value="本周">本周</li>
			                            <li data-periodEnum="3" data-value="本月">本月</li>
			                            <li data-periodEnum="4" data-value="本季度">本季度</li>
			                            <li data-periodEnum="5" data-value="本年">本年</li>
					                </ul>
                            </div>
                        </div>
                        
                         <div class="marginTop10 filterFieldAreaHidden">
                                <div class="selectWapper select fl">
                                   <p class="selectedDiv" data-value="指定字段">指定字段</p>                                  
                                    <ul class="filterfieldUl hasField">
                                        
                                    </ul>
                                </div>
                                <div class="selectWapper marginLeft8 select fl">
                                     <p class="selectedDiv" data-value="指定范围">指定范围</p>
                                     <ul class="appointfiledscope">
                                     	<div class="allcheck"><label><input type="checkbox">全选</label></div>
                                     	<ol>
                                     	
                                     	</ol>
                                     	<div class="fieldrangebtn">
                                     		<div class="fl fieldrangeok ac">确定</div>
                                     		<div class="fr fieldrangecancel ac">取消</div>
                                     	</div>
                                     </ul>
                                </div>
                                <div class="fl marginLeft8 warningeditaddrem">
                                    <span class="editibtnrem"><img src="img/editifodel1.png"></span> 
                                    <span class="editibtndd marginLeft8"><img src="img/editifoadd.png"></span>
                                                                              
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        <div class="marginTop13" id="fieldArea">
                            <p >筛选字段 <span>(在指定条件下进行计算)</span></p>
                            <div class="marginTop10 filterFieldArea">
                                <div class="selectWapper select fl">
                                   <p class="selectedDiv" data-value="指定字段">指定字段</p>                                  
					                <ul class="filterfieldUl hasField">
					                    
					                </ul>
                                </div>
                                <div class="selectWapper marginLeft8 select fl">
                                     <p class="selectedDiv" data-value="指定范围">指定范围</p>
                                     <ul class="appointfiledscope">
                                     	<div class="allcheck"><label><input type="checkbox">全选</label></div>
                                     	<ol>
                                     	
                                     	</ol>
                                     	<div class="fieldrangebtn">
                                     		<div class="fl fieldrangeok ac">确定</div>
                                     		<div class="fr fieldrangecancel ac">取消</div>
                                     	</div>
                                     </ul>
                                </div>
								<div class="fl marginLeft8 warningeditaddrem">
			                     	<span class="editibtnrem opa25"><img src="img/editifodel1.png"></span> 
			                        <span class="editibtndd marginLeft8"><img src="img/editifoadd.png"></span>
		                                                                      
		                    	</div>
		                    	<div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="newWarnTagSet">
                    <div class="newWarnTagSetMargin"><span class="titleSpan">预警条件设置</span><p class="titleP"></p>
                    </div>
                    <div>
                        <div id="newWarnCond" class="selectWapper marginTop10 select fl">
                               <p class="selectedDiv" data-value="等于" data-cond="EQUAL">等于</p>                                  
					                <ul>
					                	<li data-value="等于" class="Selected" data-cond="EQUAL">等于</li>
								        <li data-value="不等于" data-cond="NOTEQUAL">不等于</li>
								        <li data-value="大于" data-cond="GREATERTHAN">大于</li>
								        <li data-value="小于" data-cond="LESSTHAN">小于</li>
								        <li data-value="大于等于" data-cond="GREATERTHANOREQUAL">大于等于</li>
								        <li data-value="小于等于" data-cond="LESSTHANOREQUAL">小于等于</li>
								        <li data-value="介于" data-cond="BETWEEN">介于</li>
								        <li data-value="不介于" data-cond="NOTBETWEEN">不介于</li>					                 
					                </ul>
                        </div>
                        <div id="doubleInput" class="fl">
                            <input type="number" id="warnValue1" placeholder="请输入数字" class="numInPut marginLeft5" />
                                <img src="img/wave.png">
                            <input type="number" id="warnValue2" placeholder="请输入数字" class="numInPut" />
                        </div>
                        <div id="singleInput" class="fl">
                            <input type="number" id="warnValue" placeholder="请输入数字" class="newWarnKeyWords marginLeft5" />
                        </div>
                    </div>
                </div>
                <span class="clearfix"></span>
                <div style="margin-top:19px">
                    <div id="newWarnConfirm" class="newWarnConfirm">确定</div>
                    <div id="newWarnCancle" class="newWarnCancle">取消</div>
                    <div style="float:right;line-height:24px">高级选项</div>
                </div>
            </div>
        </div>
   </div>