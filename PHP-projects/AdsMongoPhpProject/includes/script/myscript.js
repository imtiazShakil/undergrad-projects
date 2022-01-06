$(document).ready( 
        function() {
            
            $("#insertForm").hide();
            $("#deleteForm").hide();
            $("#updateForm").hide();
            $("#findForm").hide();
            
            hide_buttons();
        }
);

$counter=0;
$fadeOutTime=200;
$fadeInTime=1000;
$("#connectButton").click(
        function()
        {
              $.post(
                    "getConnection.php",null,
                    function(data) {
                        $("#status").html(data.toString());
                    }
                );
            
            $.post(
                    "getAllDatabaseNames.php",null,function(data) {
                        $("#dbNames").html(data.toString());
                    }
                );
        }
                    );
                        
$("#getTableButton").click(
        function () {
            $.post(
                    "getTable.php",{query:$("#dbName").val()}, function(data) {
                        $("#collectionNames").html(data.toString());
                    }
                );
        }
        );
            
$("#loadDataButton").click(
        function() {
            $.post(
                    "loadData.php",
                    {dbName:$("#dbName").val() , tableName:$("#selectedCollection").val() },
                    function(data) {
                        
                        $("#LoadDataSection").html(data.toString());
                        if(data.toString()!="")
                            {
                                   show_buttons();
                            }
                    }
                );
        }
        );

//---------------------Insert er kerikecha---------------------------

$("#AddInsertField").click(
            function(){
                $("#insertFormView").append(
                        "<tr>"+
                        "<td> <input type=\"text\" id=\"insKey"+$counter+"\" name=\"insKey"+$counter+"\" placeholder=\"Key\" /></td>"+
                        "<td><input type=\"text\" id=\"insVal"+$counter+"\" name=\"insVal"+$counter+"\" placeholder=\"Value\" /></td>"+ 
                        "</tr>"
                    );
                 
                 $counter++;
            }
        );
            
$("#clearCounterInsertButton").click( 
    function() {
        $("#insertFormView").html("");
        $counter=0;
    }
);
$("#confirmInsertButton").click(
    function() {
        
        $("#InsdbName").val( $("#dbName").val() );
        $("#InsCollection").val($( "#selectedCollection").val() );
        
        
        $.post(
                "insert.php",
                $("#insertForm :input").serializeArray(),
                function(data) {
//                    alert(data.toString());
                    $("#status").html("");
                    $("#status").fadeOut($fadeOutTime);
                    $("#status").html(data.toString());
                    $("#status").fadeIn($fadeInTime);
                }
            );
    }
);
//---------------------------------------------------------------------------------

$("#insertButton").click(
        function() {
            $("#insertForm").show(300);
            $("#deleteForm").hide(300);
            $("#updateForm").hide(300);
            $("#findForm").hide(300);
            
            $("#updateFormView").html("");
            $("#insertFormView").html("");
            $("#deleteFormView").html("");
            $("#findFormView").html("");
            $counter=0;
        }
        );
            
$("#deleteButton").click(
        function() {
            $("#insertForm").hide(300);
            $("#deleteForm").show(300);
            $("#updateForm").hide(300);
            $("#findForm").hide(300);
            
            $("#updateFormView").html("");
            $("#insertFormView").html("");
            $("#deleteFormView").html("");
            $("#findFormView").html("");
            $counter=0;
        }
        );
$("#updateButton").click(
        function() {
    
            $("#insertForm").hide(300);
            $("#deleteForm").hide(300);
            $("#updateForm").show(300);
            $("#findForm").hide(300);
            
            $("#updateFormView").html("");
            $("#insertFormView").html("");
            $("#deleteFormView").html("");
            $("#findFormView").html("");
            $counter=0;
        }
        );

$("#findButton").click(
        function() {
            $("#insertForm").hide(300);
            $("#deleteForm").hide(300);
            $("#updateForm").hide(300);
            $("#findForm").show(300);
            
            $("#updateFormView").html("");
            $("#insertFormView").html("");
            $("#deleteFormView").html("");
            $("#findFormView").html("");
            $counter=0;
        }
        );

            
//---------------------Delete er kerikecha---------------------------
$("#delInsertField").click(
            function(){
                $("#deleteFormView").append(
                        "<tr>"+
                        "<td> <input type=\"text\" id=\"delKey"+$counter+"\" name=\"delKey"+$counter+"\" placeholder=\"Key\" /></td>"+
                        "<td><input type=\"text\" id=\"delVal"+$counter+"\" name=\"delVal"+$counter+"\" placeholder=\"Value\" /></td>"+ 
                        "</tr>"
                    );
                 
                 $counter++;
            }
        );





$("#clearCounterDeleteButton").click( 
    function() {
        $("#deleteFormView").html("");
        $counter=0;
    }
);
$("#confirmDeleteButton").click(
    function() {
        
        $("#deldbName").val( $("#dbName").val() );
        $("#delCollection").val($( "#selectedCollection").val() );
        
        
        $.post(
                "delete.php",
                $("#deleteForm :input").serializeArray(),
                function(data) {
//                    alert(data.toString());
                    $("#status").html("");
                    $("#status").fadeOut($fadeOutTime);
                    $("#status").html(data.toString());
                    $("#status").fadeIn($fadeInTime);
                }
            );
    }
);
            

//---------------------Update er kerikecha---------------------------
$("#updInsertField").click(
            function(){
                $("#updateFormView").append(
                        "<tr>"+
                        "<td> <input type=\"text\" id=\"fndKey"+$counter+"\" name=\"fndKey"+$counter+"\" placeholder=\"Find Key\" /></td>"+
                        "<td><input type=\"text\" id=\"fndVal"+$counter+"\" name=\"fndVal"+$counter+"\" placeholder=\"Find Value\" /></td>"+
                        "</tr>"
                    );
                 
                 $counter++;
            }
        );

$("#updInsertNewField").click(
            function(){
                $("#updateFormView").append(
                        "<tr>"+
                        "<td> <input type=\"text\" id=\"updKey"+$counter+"\" name=\"updKey"+$counter+"\" placeholder=\"Updated Key\" /></td>"+
                        "<td><input type=\"text\" id=\"updVal"+$counter+"\" name=\"updVal"+$counter+"\" placeholder=\"Updated Value\" /></td>"+
                        "</tr>"
                    );
                 
                 $counter++;
            }
        );



$("#clearCounterUpdateButton").click( 
    function() {
        $("#updateFormView").html("");
        $counter=0;
    }
);
    
$("#clearCounterUpdateNewButton").click( 
    function() {
        $("#updateFormView").html("");
        $counter=0;
    }
);
$("#confirmUpdateButton").click(
    function() {
        
        $("#upddbName").val( $("#dbName").val() );
        $("#updCollection").val($( "#selectedCollection").val() );
        
        
        $.post(
                "update.php",
                $("#updateForm :input").serializeArray(),
                function(data) {
//                    alert(data.toString());
                    $("#status").html("");
                    $("#status").fadeOut($fadeOutTime);
                    $("#status").html(data.toString());
                    $("#status").fadeIn($fadeInTime);
                }
            );
    }
);
    
//---------------------Find er kerikecha---------------------------
$("#fndInsertField").click(
            function(){
                $("#findFormView").append(
                        "<tr>"+
                        "<td> <input type=\"text\" id=\"fndKey"+$counter+"\" name=\"fndKey"+$counter+"\" placeholder=\"Find Key\" /></td>"+
                        "<td><input type=\"text\" id=\"fndVal"+$counter+"\" name=\"fndVal"+$counter+"\" placeholder=\"Find Value\" /></td>"+ 
                        "</tr>"
                    );
                 
                 $counter++;
            }
        );





$("#clearCounterFindButton").click( 
    function() {
        $("#findFormView").html("");
        $counter=0;
    }
);
$("#confirmFindButton").click(
    function() {
        
        $("#fnddbName").val( $("#dbName").val() );
        $("#fndCollection").val($( "#selectedCollection").val() );
        
        
        $.post(
                "find.php",
                $("#findForm :input").serializeArray(),
                function(data) {
                    $("#status").html("");
                    $("#status").fadeOut($fadeOutTime);
                    $("#status").html(data.toString());
                    $("#status").fadeIn($fadeInTime);
                }
            );
    }
);

function hide_buttons()
{
    $("#insertButton").hide();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#findButton").hide();
}

function show_buttons()
{
    $("#insertButton").fadeIn(300);
    $("#updateButton").fadeIn(400);
    $("#deleteButton").fadeIn(500);
    $("#findButton").fadeIn(600);
}