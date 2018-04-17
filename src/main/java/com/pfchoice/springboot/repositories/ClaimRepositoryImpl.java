package com.pfchoice.springboot.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.pfchoice.springboot.model.ClaimDTO;

public class ClaimRepositoryImpl implements ClaimRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;


    @SuppressWarnings("unchecked")
	@Override
    public List<ClaimDTO> getClaims(final int firstPosition, final int pageSize, final int teamAssignments, final String sSearch, final String allocationDate, final String sort, final String sortdir, 
			final String practices, final String remarks, final String srvcDtFrom, final String srvcDtTo, final String patientName, final String birthDate,	final String insurances, 
			final String insuranceTypes, final Double chargesMin, final Double chargesMax, final String claimStatus, final String priorities,	
			final String tableName, final Integer userId,	final Integer roleId) {
        StoredProcedureQuery claimStatuses =
              em.createNamedStoredProcedureQuery("claimStatuses");
        claimStatuses.setParameter("firstPosition", 0);
        claimStatuses.setParameter("pageSize", 10);
        claimStatuses.setParameter("teamAssignment", 0);
        claimStatuses.setParameter("search", "");
        claimStatuses.setParameter("allocationDate", allocationDate);
        claimStatuses.setParameter("practices", practices);
        claimStatuses.setParameter("remarks", remarks);
        claimStatuses.setParameter("serviceDtFrom", srvcDtFrom);
        claimStatuses.setParameter("serviceDtTo", srvcDtTo);
        claimStatuses.setParameter("patientName", patientName);
        claimStatuses.setParameter("birthDt", birthDate);
        claimStatuses.setParameter("insurances", insurances);
        claimStatuses.setParameter("insuranceTypes", insuranceTypes);
        claimStatuses.setParameter("chargesMin", chargesMin);
        claimStatuses.setParameter("chargesMax", chargesMin);
        claimStatuses.setParameter("claimStatuses", claimStatus);
        claimStatuses.setParameter("priorities", priorities);
        claimStatuses.setParameter("tableName", tableName);
        claimStatuses.setParameter("userId", userId);
        claimStatuses.setParameter("roleId", roleId);

        return (List<ClaimDTO>) claimStatuses.getResultList();
        
    }
    
}
