$(document).ready( 
        function() {
            $("#formInsert").hide();
            $("#formUpdate").hide();
            $("#formDelete").hide();
            
            $("#insert").fadeOut(200);
            $("#update").fadeOut(200);
            $("#delete").fadeOut(200);
            $("#show").fadeOut(200);
            $("#tableName").fadeOut(200);
            $("#lbtbnm").fadeOut(200);
            
            $("#lbcq").fadeOut(200);
            $("#quer").fadeOut(200);
            $("#fazlami").fadeOut(200);
            
            
        }
);

$("button#submit").click(
        function() {
            
            $("#status").empty();
            
            if( $("#name").val()=="" || $("#loginPassword").val()=="" ) 
                $("#status").html("Username and Password can't be left blank");
            else 
                $.post($("#loginForm").attr("action") ,
                    $("#loginForm :input").serializeArray(),
                    function(data) {
                        $("#status").html(data);
//                        alert(data.toString());
//                        draw_table("show tables from test ;","#fortb2");
                        if(data.toString()=="Connection With The Database is Successful</br>Login Successful") { 
                            draw_table("show tables from test ;","#fortb2");
                            
                            $("#formInsert").show();
                            $("#formUpdate").show();
                            $("#formDelete").show();
                            
                            $("#insert").fadeIn(500);
                            $("#update").fadeIn(500);
                            $("#delete").fadeIn(500);
                            $("#show").fadeIn(500);
                            $("#tableName").fadeIn(500);
                            $("#lbtbnm").fadeIn(500);
                            
                            $("#lbcq").fadeIn(500);
                            $("#quer").fadeIn(500);
                            $("#fazlami").fadeIn(500);
                        }
                    }
                );
                    
            $("#loginForm").submit(
                    function() {
                        
                        return  false;
                    }
            );
            
        }
);

$("#fazlami").click(
    function() {
        $.post(
               "includes/get_table.php",
               {query: $("#quer").val()} ,
                function(data) {
//                   alert(data);
                   var str="<table border=\"2\">";
                   $("#fortb").html(str+data+"</table>");
               }
        
        );
//        $("#quer").ch
    }
);

function draw_table(__querystr, getid) {
//    alert(__querystr);
//    alert(getid);
    
    $.post(
            "includes/get_table.php",
            {query : __querystr },
            function(data){
//                alert("received data :"+data);
                var str="<table border=\"2\">";
                $(getid).html(str+data+"</table>");
                
            }
    );
}

$("#insert").click(
        function() {
                           
            clear_all_fields();
            $.post(
                    "includes/get_form.php",
                    {query : "SELECT `COLUMN_NAME`"+
                                "FROM `INFORMATION_SCHEMA`.`COLUMNS`"+ 
                                "WHERE `TABLE_SCHEMA`='test'"+ 
                                    "AND `TABLE_NAME`='"+$("#tableName").val() +"';"},
                     function(data) {
                        
                         var obj =jQuery.parseJSON(data.toString());
                         $("#formdesign").append("<input type=\"hidden\"  name=\"0\" value=\""+$("#tableName").val().toString()+"\"/></br>"); 
                         $(obj).each (
                                 function(val,indx) {
                                    

                                    $("#formdesign").append("<label>"+indx+"</label><input type=\"text\" id=\""+indx+"\" name=\""+(val+1)+"\" value=\"\"/></br>");                                   
//                                     $("#"+indx).on( "keyup" ,function() {
//                                            valll =$("#"+indx).val();
//                                            alert("now val"+valll);
//                                        } 
//                                    );
                                 }
                         );
                             
                       $("#formdesign").append("<input type=\"button\" id=\"insertButton\" value=\"Confirm Insertion\"/>  ");
                       
                       $("#insertButton").on("click",function() {
                            

                            $.post(
                                    "includes/insert.php",
                                    $("#formInsert :input").serializeArray(),
                                    function(data) {
                                        $("#status").html(data.toString());
//                                        alert("output_data: "+data.toString());
                                    }
                            );
                            $("#formInsert").submit(
                                function() {
                                    return false;
                                }
                            );
                            
                            } 
                        );
                            
                     }
                        
            );
            clear_all_fields();
            
        }
);
    
$("#update").click(
        function() {
                           
            clear_all_fields();
            
            $.post(
                    "includes/get_form.php",
                    {query : "SELECT `COLUMN_NAME`"+
                                "FROM `INFORMATION_SCHEMA`.`COLUMNS`"+ 
                                "WHERE `TABLE_SCHEMA`='test'"+ 
                                    "AND `TABLE_NAME`='"+$("#tableName").val() +"';"},
                     function(data) {
                        
                         var obj =jQuery.parseJSON(data.toString());
                         $("#formdesignUpdate").append("<input type=\"hidden\"  name=\"0\" value=\""+$("#tableName").val().toString()+"\"/></br>"); 
//                         $flag=0;
                         $(obj).each (
                                 function(val,indx) {
                                    $("#formdesignUpdate").append("<label>"+indx+"</label><input type=\"text\" id=\""+indx+"\" name=\""+indx+"\" value=\"\"/></br>");                                   
//                                    $flag++;
                                 }
                         );
                             
                       $("#formdesignUpdate").append("<input type=\"button\" id=\"updateButton\" value=\"Confirm Update\"/>  ");
                       $("#updateButton").on("click",function() {
                            
                            $.post(
                                    "includes/update.php",
                                    $("#formUpdate :input").serializeArray(),
                                    function(data) {
                                        $("#status").html(data.toString());
//                                        alert("output_data: "+data.toString());
                                    }
                            );
                            $("#formUpdate").submit(
                                function() {
                                    return false;
                                }
                            );
                            } 
                        );    
                     }
                        
            );
            clear_all_fields();
        }
);
    
function clear_all_fields()
{
    $("#formdesignUpdate").empty();
    $("#formdesign").empty();
    $("#formdesignDelete").empty();
    $("#status").empty();
}

$("#delete").click(
        function() {
                           
            clear_all_fields();
            
            $.post(
                    "includes/get_form.php",
                    {query : "SELECT `COLUMN_NAME`"+
                                "FROM `INFORMATION_SCHEMA`.`COLUMNS`"+ 
                                "WHERE `TABLE_SCHEMA`='test'"+ 
                                    "AND `TABLE_NAME`='"+$("#tableName").val() +"';"},
                     function(data) {
                        
                         var obj =jQuery.parseJSON(data.toString());
                         $("#formdesignDelete").append("<input type=\"hidden\"  name=\"0\" value=\""+$("#tableName").val().toString()+"\"/></br>"); 
                         $flag=0;
                         $(obj).each (
                                 function(val,indx) {
                                    if(!$flag) $("#formdesignDelete").append("<label>"+indx+"</label><input type=\"text\" id=\""+indx+"\" name=\""+indx+"\" value=\"\"/></br>");                                   
                                    else return false;
                                    $flag++;
                                 }
                         );
                             
                       $("#formdesignDelete").append("<input type=\"button\" id=\"deleteButton\" value=\"Confirm Delete\"/>  ");
                       $("#deleteButton").on("click",function() {
                            
                            $.post(
                                    "includes/delete.php",
                                    $("#formDelete :input").serializeArray(),
                                    function(data) {
//                                        alert("output_data: "+data.toString());
                                        $("#status").html(data.toString());
                                    }
                            );
                            $("#formDelete").submit(
                                function() {
                                    return false;
                                }
                            );
                            } 
                        );    
                     }
                        
            );
            clear_all_fields();
        }
);
    
$("#show").click(
        
        function() {
            clear_all_fields();
//            alert("select * from "+ $("#tableName").val() +";" );
            draw_table("select * from "+ $("#tableName").val() +";" , "#fortb");
        }
);