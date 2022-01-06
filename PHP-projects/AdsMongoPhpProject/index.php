<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ADS MONGO Php Project</title>
        <link rel="stylesheet" href="includes/basic.css" />
        <link rel="icon" href="includes/favicon.ico" type="image/x-icon"> 
        <link rel="shortcut icon" href="includes/favicon.ico" type= "image/x-icon">
    </head>
    <body>
        <div id="main">
            <div id="header"><h1>ADS MONGO Php Project</h1></div>
            <div id="nav_bar" >
                
                
                
                <table align="center" border="1px" >
                    <tr><td colspan="2" align="center"><input type="button" id="connectButton" value="Connect With MongoDB"/></td></tr>
                    <tr>
                        <td><input type="text" id="dbName" placeholder="Enter The Database Name" value=""/></td>
                        <td><input type="button" id="getTableButton" value="Load Collection"/></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="selectedCollection" placeholder="Enter The Collection Name" value=""/></td>
                        <td><input type="button" id="loadDataButton" value="Load The Data"/></td>
                    </tr>
                

                    <tr><td>Loaded Databases</td><td>Loaded Collections</td></tr>
                    <tr><td align="center"><div id="dbNames"></div></td>
                        <td align="center"><div id="collectionNames"></div></td>
                    </tr>                    
                </table>
            </div>
            
            <div id="nav_bar2">
                <div id="LoadDataSection"></div>
                
                <input type="button" id="insertButton" value="Insert"/>
                <input type="button" id="updateButton" value="Update"/>
                <input type="button" id="deleteButton" value="Delete"/>
                <input type="button" id="findButton" value="Search"/>


                <form id="insertForm"  method="post">

                    <input  type="hidden" id="InsdbName"    name="InsdbName" value=""/>
                    <input  type="hidden" id="InsCollection" name="InstableName" value=""/>
                    <table border="2px">
                        <div id="insertFormView">
                            <tr><td><input type="button" id="AddInsertField" value="Add Row"/></td>
                                <td> <input type="button" id="confirmInsertButton" value="Confirm Insertion"/>  </td>
                                <td> <input type="button" id="clearCounterInsertButton" value="Clear"/>  </td></tr>
                        </div>

                    </table>
                </form>


                <form id="deleteForm"  method="post">

                    <input  type="hidden" id="deldbName"    name="deldbName" value=""/>
                    <input  type="hidden" id="delCollection" name="deltableName" value=""/>
                    <table border="2px">
                        <div id="deleteFormView">
                            <tr><td><input type="button" id="delInsertField" value="Add Row"/></td>
                                <td> <input type="button" id="confirmDeleteButton" value="Confirm Delete"/>  </td>
                                <td> <input type="button" id="clearCounterDeleteButton" value="Clear"/>  </td></tr>
                        </div>
                    </table>
                </form>


                <form id="updateForm"  method="post">

                    <input  type="hidden" id="upddbName"    name="upddbName" value=""/>
                    <input  type="hidden" id="updCollection" name="updtableName" value=""/>
                    <table border="2px">
                        <div id="updateFormView">
                            <tr><td><input type="button" id="updInsertField" value="Add Row to Find"/></td>
                                <td> <input type="button" id="confirmUpdateButton" value="Confirm Update"/>  </td>
                                <td> <input type="button" id="clearCounterUpdateButton" value="Clear"/>  </td>

                                <td><input type="button" id="updInsertNewField" value="Add Row To Update"/></td>
                            </tr>
                        </div>
                    </table>
                </form>

                <form id="findForm"  method="post">

                    <input  type="hidden" id="fnddbName"    name="fnddbName" value=""/>
                    <input  type="hidden" id="fndCollection" name="fndtableName" value=""/>
                    <table border="2px">
                        <div id="findFormView">
                            <tr><td><input type="button" id="fndInsertField" value="Add Row"/></td>
                                <td> <input type="button" id="confirmFindButton" value="Confirm Search"/>  </td>
                                <td> <input type="button" id="clearCounterFindButton" value="Clear"/>  </td></tr>
                        </div>
                    </table>
                </form>
                
                <div id="status"></div>

            </div>
            <div id="footer"><h1>Made by Imtiaz Shakil Siddique</h1></div>
        </div>
        <script type="text/javascript" src="includes/script/jquery-1.8.2.min.js"> </script>
        <script type="text/javascript" src="includes/script/myscript.js"> </script>
    </body>
</html>
