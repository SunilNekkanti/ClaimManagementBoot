  
    
<div class="generic-container" >
   <div class="panel panel-success" >
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="claim">Claim List</span> 
               <button type="button"   ng-click="ctrl.addClaim()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editClaim(ctrl.claimId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeClaim(ctrl.claimId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        
        
        <div class="table-responsive" ng-if="ctrl.displayTables[0]">
			<div class="panel-body">
               <tabset>
                <!-- admin and team lead can view this tab -->
                <tab  heading="My Team Assignments" ng-click="ctrl.setTeamAssignment(1)" active="(ctrl.teamAssignments ==1)">
                	<div class="table-responsive" ng-if="ctrl.displayTables[1]">
                	<table datatable="" id="content" dt-options="ctrl.dt1Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                <!-- admin and team lead can view this tab -->
                 <tab  heading="Worked Assignments" ng-click="ctrl.setTeamAssignment(2)" active="(ctrl.teamAssignments ==2)">
                    <div class="table-responsive" ng-if="ctrl.displayTables[2]">
                	<table datatable="" id="content" dt-options="ctrl.dt2Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dt2Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                <!--  all non Admin roles can view this tab -->
                <tab  heading="My Assignments" ng-click="ctrl.setTeamAssignment(3)" active="(ctrl.teamAssignments ==3)">
                    <div class="table-responsive" ng-if="ctrl.displayTables[3]">
                	<table datatable="" id="content" dt-options="ctrl.dt3Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dt3Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                 <!-- admin and team lead can view this tab -->
                 <tab  heading="Completed Claims"ng-click="ctrl.setTeamAssignment(4)" active="(ctrl.teamAssignments ==4)">
                 <div class="table-responsive" ng-if="ctrl.displayTables[4]" >
                 <table datatable="" id="content4" dt-options="ctrl.dt4Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dt4Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        		  </div>		
                </tab>
                 <!-- admin and team lead can view this tab -->
                 <tab  heading="Allocated Outstanding" ng-click="ctrl.setTeamAssignment(5)" active="(ctrl.teamAssignments ==5)">
                  <div class="table-responsive" ng-if="ctrl.displayTables[5]">
                	<table datatable="" id="content" dt-options="ctrl.dt5Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dt5Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                 <!-- admin and team lead can view this tab -->
                  <tab  heading="Unallocated Oustanding" ng-click="ctrl.setTeamAssignment(6)" active="(ctrl.teamAssignments ==6)">
                 	 <div  class="table-responsive" ng-if="ctrl.displayTables[6]">
                	<table datatable="" id="content" dt-options="ctrl.dt6Options" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dt6Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                 <!-- admin and team lead can view this tab -->
                 <tab  heading="Total Outstanding" ng-click="ctrl.setTeamAssignment(0)" active="(ctrl.teamAssignments ==0)">
                 <div class="table-responsive" ng-if="ctrl.displayTables[0]">
                	<table datatable="" id="content" dt-options="ctrl.dtOptions" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
        			</div>
                </tab>
                </tablset>

      </div>
    </div>
  </div>

    
    
</div>