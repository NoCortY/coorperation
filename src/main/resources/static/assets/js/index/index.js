$(function(){
    var getCategoryRequestUrl = "/categorycontroller/getallcategory";
    getCategoryCard();









    function getCategoryCard(){
        $.ajax({
            url:getCategoryRequestUrl,
            type:'GET',
            success:function(data){
                if(data.successFlag){
                    var htmlStr = "";
                    $.each(data.categoryList,function(index,item){
                        htmlStr+="<div class='fl-col'>" +
                            "<div class='course_box'>" +
                            "<a href='course-list.html'>" +
                            "<div class='icon_relative'>" +
                            "<i class='la "+item.categoryIconCode+"' aria-hidden='true'></i>" +
                            "<span>"+item.categoryName+"</span>" +
                            "</div>" +
                            "</a>" +
                            "</div>" +
                            "</div>";
                    });
                    $("#categorylist").html(htmlStr);
                }else{
                    alert(data.message);
                }
            }
        });
    }
});