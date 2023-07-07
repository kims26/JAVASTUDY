//$(".main_menu span").css("width", "0px");
//$(".main_menu >li").mouseover(function () {
//    $(this).find("span").stop().animate({
//        "width": "300px"
//    }, "slow", function () {
//
//        $(this).find(".ko").css("display","block");
//
//    });
//
//
//});
//
//$(".main_menu li").mouseout(function () {
//    $(this).find("span").stop().animate({
//        "width": "0px"
//    }, "fast");
//});

$(".main_menu span").css("width", "0px");
var liList = $(".main_menu >li");

liList.mouseenter(function () {

    $(this).find("span").stop().animate({

        "width": "300px"

    }, 500, function () {



        $(this).parent().find("ul").show();

    });

});



liList.mouseleave(function () {

    $(this).find("span").stop().animate({

        "width": "0px"

    }, "fast", function () {

        $(this).parent().find("ul").hide();

    });

});
$('.museum').hide();
var myTimer = setInterval(function () {
    //   $('.jewerly').fadeOut(1000,function(){
    //       $('.museum').fadeOut(1000,function(){
    //            $('.jewerly').fadeIn(1000,function(){
    //                  $('.museum').fadeIn(1000);
    //       });
    //   });
    //   });  
    $('.jewerly').fadeOut(2000).delay(2000);
    $('.museum').fadeIn(2000).delay(2000);
    $('.museum').fadeOut(2000).delay(2000);
    $('.jewerly').fadeIn(2000).delay(2000);
    //


}, 2000);
//
//$("#1st").click(function () { //시작버튼 눌렀을때 
//      $(".tour_imformation a").attr({
//           "src":"../images/sub/tv-img1.png",
//                "alt":"img1"
//    })
//      $(".plug span").css({
//        "width": "145px"
//    });
//    
//});
//$("#2st").click(function () { //시작버튼 눌렀을때 
//    $(".tour_imformation a").attr({
//           "src":"../images/sub/tv-img2.png",
//                "alt":"img2"
//    })
//      $(".plug span").css({
//        "width": "380px"
//    });
//});
//
//$("#3st").click(function () { //시작버튼 눌렀을때 
//    $("#im2").css({
//        "display": "block"
//    })
//      $(".plug span").css({
//        "width": "620px"
//    });
//        return false;
//        return false;
//});
//$("#4st").click(function () { //시작버튼 눌렀을때 
//    $("#im4").css({
//        "display": "block"
//    });
//      $(".plug span").css({
//        "width": "860px"
//    });
//        return false;
//});
//$("#5st").click(function () { //시작버튼 눌렀을때 
//    $("#im5").css({
//        "display": "block"
//    });
//      $(".plug span").css({
//        "width": "1098px"
//    });
//        return false;
//});


$(".s_logo").hide();
$(".s_logo").fadeIn(2000);

$("nav.s_gnb").hide();
$("nav.s_gnb").fadeIn(2000);


$('.background ul li:nth-child(1) a').hide();
//
//$('.background ul li:nth-child(1)').delay('100').animate({
//
//     left: -80,
//   opacity: 0.4,
//bottom: 260
//
//
//},2000);
$('.background ul li:nth-child(1) a').fadeIn(2500);
//       
//        
//         
$('.background ul li:nth-child(2) a').hide();
//         $('.background ul li:nth-child(2)').animate({
//
//           top:"350px",
//            
//             
//        },1500);
$('.background ul li:nth-child(2) a').fadeIn(2000);



