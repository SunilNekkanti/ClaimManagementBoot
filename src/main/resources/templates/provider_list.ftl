<div class="generic-container">
  <div class="panel panel-success" ng-if="!ctrl.display">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="prvdr ">List of Providers</span>
      <button type="button" ng-click="ctrl.addProvider()" ng-hide="ctrl.displayEditButton" class="btn btn-success btn-xs custom-width floatRight"> Add </button>
     
      
    </div>
    <div class="table-responsive">
      <div class="panel-body">

        <table datatable="" id="content" dt-options="ctrl.dtOptions" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover " cellspacing="0" width="100%"></table>
      </div>
    </div>
  </div>


  <div class="panel panel-success" ng-if="ctrl.display">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Provider </span></div>
    <div class="panel-body">
      <div class="formcontainer">
        <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
        <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
        <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
          <input type="hidden" ng-model="ctrl.prvdr.id" />
         <div class="row">
        
         
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Provider Name</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.prvdr.name" id="uname" class="username form-control input-sm" placeholder="Enter Provider name" required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Practice Name</label>
	                        <div class="col-md-3">
	                        <select class=" form-control" ng-model="ctrl.prvdr.practice" name="practice" ng-options="practice.name for practice in ctrl.practices | orderBy:'name' track by practice.name" required>
	                          <!--  <input type="text" ng-model="ctrl.claimStatusDetail.claimStatusId" id="uname" class="username form-control input-sm" placeholder="Enter ClaimStatus name" required ng-minlength="4"/> -->
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Practice NPI</label>
	                        <div class="col-md-3">
	                         <select class=" form-control" ng-model="ctrl.prvdr.practice" name="practice" ng-options="practice.npi for practice in ctrl.practices | orderBy:'npi' track by practice.npi" required>
	                          <!--  <input type="text" ng-model="ctrl.claimStatusDetail.claimStatusId" id="uname" class="username form-control input-sm" placeholder="Enter ClaimStatus name" required ng-minlength="4"/> -->
	                        </div>
	                    </div>
	                   
	                   <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Practice TaxID</label>
	                        <div class="col-md-3">
	                         <select class=" form-control" ng-model="ctrl.prvdr.practice" name="practice" ng-options="practice.taxId for practice in ctrl.practices | orderBy:'taxId' track by practice.taxId" required>
	                          <!--  <input type="text" ng-model="ctrl.claimStatusDetail.claimStatusId" id="uname" class="username form-control input-sm" placeholder="Enter ClaimStatus name" required ng-minlength="4"/> -->
	                        </div>
	                    </div>  
	                    
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">NPI</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.prvdr.npi" id="uname" class="username form-control input-sm" placeholder="Enter NPI" required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Code</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.prvdr.code" id="uname" class="username form-control input-sm" placeholder="Enter code" required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                    
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Contact</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.prvdr.contact" id="uname" class="username form-control input-sm" placeholder="Enter Contact " required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Facility Address</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.prvdr.facilityAddress" id="uname" class="username form-control input-sm" placeholder="Enter facilityAddress" required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                </div>
	                
          <div class="row">
            <div class="form-actions floatRight col-md-offset-10">
              <input type="submit" value="{{!ctrl.prvdr.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs"  ng-show="!ctrl.prvdr.id" ng-disabled="myForm.$pristine">Reset Form</button>
              <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
              <button type="button" ng-click="ctrl.removeProvider(ctrl.prvdr.id)" class="btn btn-danger btn-xs" ng-show="ctrl.prvdr.id" >Delete</button> 
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>


</div>