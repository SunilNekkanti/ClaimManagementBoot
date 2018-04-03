<!DOCTYPE html>
<html ng-app="my-app">

<head>
  <link href="css/app.css" rel="stylesheet" />
  <link href="css/bootstrap.css" rel="stylesheet" />
  <link href="css/ui-navbar.css" rel="stylesheet" />
  <link href="css/bootstrap-multiselect.css" rel="stylesheet" />

  <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/bootstrap/datatables.bootstrap.min.css" />
  <link rel="stylesheet" href="css/bootstrap-datepicker.min.css" />
  <link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.4/css/fixedColumns.dataTables.min.css" />
  <link rel="stylesheet" href="//cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.min.css" />
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  <script src="js/lib/angular.min.js"></script>
  <script src="js/lib/angular-resource.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/bootstrap/angular-datatables.bootstrap.min.js"></script>
  <script src="js/lib/moment-with-locales.js"></script>
  <script src="js/lib/bootstrap.min.js"></script>
  <script src="js/lib/bootstrap-datepicker.min.js"></script>
  <script src="js/lib/bootstrap-datetimepicker.min.js"></script>
  <script src="js/lib/dataTables.bootstrap.min.js"></script>
  <script src="js/lib/angular-animate.js"></script>
  <script src="js/lib/angular-sanitize.min.js"></script>
  <script src="js/lib/ui-bootstrap-tpls-2.5.0.min.js"></script>
  <script src="js/lib/angular-datatables.min.js"></script>
  <script src="js/lib/ngStorage.min.js"></script>
  <script src="js/lib/angular-ui-router.min.js"></script>
  <script src="js/lib/ui-bootstrap-tpls-0.12.0.min.js"></script>
  <script src="js/lib/angular-bootstrap-multiselect.min.js"></script>
  <script src="js/lib/ocLazyLoad.js"></script>
  <script src="https://cdn.datatables.net/fixedcolumns/3.2.4/js/dataTables.fixedColumns.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.min.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/buttons/angular-datatables.buttons.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/fixedcolumns/angular-datatables.fixedcolumns.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/fixedheader/angular-datatables.fixedheader.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-datatables/0.6.2/plugins/tabletools/angular-datatables.tabletools.min.js"></script>


  <script src="js/app/app.js"></script>
  <script src="js/app/UserService.js"></script>
  <script src="js/app/datetimepicker.js"></script>
  <script src="js/app/datetimepicker.templates.js"></script>

  <style>
    .dataTable>thead>tr>th[class*="sort"]::after {
      display: none
    }
  </style>

</head>

<body class="ng-cloak" ng-controller="NavbarController">
  <nav class="navbar   navbar-inverse" ng-if="displayNavbar" role="navigation">
    <div class="container-fluid">

      <div class="navbar-collapse collapse in " collapse="isCollapsed" aria-expanded="true">
        <ul class="nav navbar-nav">
          <li><a ui-sref="main" ui-sref-active="active">Home</a> </li>
          <li class="dropdown" dropdown ng-if="(loginUser.roleName === 'ADMIN' || loginUser.roleName === 'MANAGER')">
            <a href="#" class="dropdown-toggle" dropdown-toggle role="button" aria-expanded="false">Lookups<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
			  <li><a ui-sref="main.fileUpload" ui-sref-active="active">File Uploads</a> </li>
              <li><a ui-sref="main.user" ui-sref-active="active">User Accounts</a> </li>
              <li><a ui-sref="main.claimAllocation" ui-sref-active="active">Claim Allocation</a> </li>
              <li class="divider"></li>
              <li class="dropdown-submenu" dropdown>
                <a href="#" class="dropdown-toggle" dropdown-toggle role="button" aria-expanded="false">Lookups</a>
                <ul class="dropdown-menu" role="submenu"> 
                  <li><a ui-sref="main.allocation" ui-sref-active="active">Allocation</a></li>
                  <li><a ui-sref="main.allocationLevel" ui-sref-active="active">Allocation Level</a></li>
                  <li><a ui-sref="main.claimStatus" ui-sref-active="active">Claim Status</a> </li>
                  <li><a ui-sref="main.claimStatusDetail" ui-sref-active="active">Claim Status Detail</a></li>
                  <li><a ui-sref="main.insurance" ui-sref-active="active">Insurance</a></li>
                  <li><a ui-sref="main.mappingInsurance" ui-sref-active="active">Mapping Insurance</a></li>
                  <li><a ui-sref="main.provider" ui-sref-active="active">Provider</a></li>
                  <li><a ui-sref="main.providerInsuranceDetails" ui-sref-active="active">Provider Insurance Detail</a></li>
                  <li><a ui-sref="main.practice" ui-sref-active="active">Practice</a></li>
                  <li><a ui-sref="main.priority" ui-sref-active="active">Priority</a></li>
                  <li><a ui-sref="main.role" ui-sref-active="active">Role</a></li>
                  <li><a ui-sref="main.target" ui-sref-active="active">Target</a></li>
                  <li><a ui-sref="main.weightage" ui-sref-active="active">Weightage</a></li>
                  <li><a ui-sref="main.weightageType" ui-sref-active="active">Weightage Type</a></li>
                  
              </ul>
          </li>
              </ul>
              </li>
          

         
          <li class="dropdown" dropdown ng-if="(loginUser.roleName === 'ADMIN' || loginUser.roleName === 'MANAGER')">
            <a href="#" class="dropdown-toggle" dropdown-toggle role="button" aria-expanded="false">Reports <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a ui-sref="main.membershipHedis" ui-sref-active="active">Dashboard</a></li>
              <li><a ui-sref="main.membershipHospitalization" ui-sref-active="active">Provider Requests</a></li>
              <li><a ui-sref="main.membershipClaims" ui-sref-active="active">User Report</a></li> 
            
            </ul>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li> <a href="#"> <span class="glyphicon glyphicon-user"></span> ${username}</a></li>
          <li> <a ng-click="callMe('logout')"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
      </div>
    </div>
  </nav>


  <ui-view> </ui-view>
</body>

</html>
