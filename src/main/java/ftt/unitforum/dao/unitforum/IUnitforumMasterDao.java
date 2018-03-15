package ftt.unitforum.dao.unitforum;

import org.apache.ibatis.annotations.Param;
import ftt.unitforum.types.UnitforumMaster;

public interface IUnitforumMasterDao {
	public UnitforumMaster getMasterInfo(@Param("ssn") int ssn, @Param("master_idx") int master_idx);
}