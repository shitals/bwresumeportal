<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bitwise - Resume Portal</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Dropdown Checkboxes -->
    <link rel="stylesheet" href="css/dropdownCheckboxes.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; ">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
<!--                 <img src="../images/bwlogo1.png"/>
 -->                <a class="navbar-brand" href="index.html">Bitwise - Resume Portal v0.1</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.html"><i class="fa fa-cloud fa-fw"></i> Resume Portal - Home</a>
                        </li>
                        <li>
                            <a href="bw-upload.html"><i class="fa fa-cloud-upload fa-fw"></i> Upload</a>
                        </li>
                        <li>
                            <a href="bw-search.html"><i class="fa fa-search fa-fw"></i> Search</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        <i class="fa fa-cloud-upload fa-1x"></i>
                        Upload Profile
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">

<fieldset>

<!-- Select Basic -->
<div class="form-group">
<label class="col-md-4 control-label" for="selectbasic">Select Skills</label>

  <div class="col-md-4 dropdown cq-dropdown" data-name='statuses'>
      <button class="btn btn-default btn-sm dropdown-toggle" type="button" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> Category <span class="caret"></span> </button>
      <ul class="dropdown-menu" aria-labelledby="dropdown1">
            <li><label class="radio-btn"><input type="checkbox" value='Tableau'>Tableau</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='PHP'>PHP</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Kafka'>Kafka</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Selenium'>Selenium</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='SOA'>SOA</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Jmeter'>Jmeter</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Devtest'>Devtest</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='ETL'>ETL</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Docker'>Docker</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Gitlab'>Gitlab</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='.NET'>.NET</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='BA'>BA</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Data Warehousing'>Data Warehousing</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='QA Automation'>QA Automation</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='GCF'>GCF</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Cloud Foundry'>Cloud Foundry</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='PCF'>PCF</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Java Microservices'>Java Microservices</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='Machine learning'>Machine learning</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='R'>R</label></li>
            <li><label class="radio-btn"><input type="checkbox" value='SharePoint'>SharePoint</label></li>
        <li class='text-center'>
          <button type='button' class='btn btn-xs btn-default clear close-dropdown' value='Clear'>Clear</button>
          <button type='button' class='btn btn-xs btn-default save' value='Save'>Save</button>
        </li>
      </ul>
    </div>
</div>

<!-- File Button --> 
<div class="form-group">
  <label class="col-md-4 control-label" for="filebutton">Upload Resume</label>
  <div class="col-md-4">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="singlebutton"></label>
  <div class="col-md-4">
  <!--  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button> -->
     
  </div>
</div>

</fieldset>

<form method="POST" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

<#if uploadSuccess?? && uploadSuccess='true'>
 File uploaded successfully
</#if>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <!-- <script src="../vendor/raphael/raphael.min.js"></script>
    <script src="../vendor/morrisjs/morris.min.js"></script>
    <script src="../data/morris-data.js"></script> -->

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

    <!-- Dropdown Checkboxes -->
    <script src="js/dropdownCheckboxes.min.js"></script>

</body>

</html>
