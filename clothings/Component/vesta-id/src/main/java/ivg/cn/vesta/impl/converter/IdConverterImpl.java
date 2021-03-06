package ivg.cn.vesta.impl.converter;

import ivg.cn.vesta.Id;
import ivg.cn.vesta.impl.bean.IdMeta;

public class IdConverterImpl implements IdConverter{

	@Override
	public long convert(Id id, IdMeta idMeta) {
		return doConvert(id, idMeta);
	}
	
	protected long doConvert(Id id, IdMeta idMeta) {
        long ret = 0;

        ret |= id.getMachine();

        ret |= id.getSeq() << idMeta.getSeqBitsStartPos();

        ret |= id.getTime() << idMeta.getTimeBitsStartPos();

        ret |= id.getGenMethod() << idMeta.getGenMethodBitsStartPos();

        ret |= id.getType() << idMeta.getTypeBitsStartPos();

        ret |= id.getVersion() << idMeta.getVersionBitsStartPos();

        return ret;
    }

	@Override
	public Id convert(long id, IdMeta idMeta) {
		Id ret = new Id();
		ret.setMachine(id & idMeta.getMachineBitsMask());
		
		ret.setSeq((id >>> idMeta.getSeqBitsStartPos()) & idMeta.getSeqBitsMask());
		
		ret.setTime((id >>> idMeta.getTimeBitsStartPos()) & idMeta.getTimeBitsMask());
		
		ret.setGenMethod((id >>> idMeta.getGenMethodBitsStartPos()) & idMeta.getGenMethodBitsMask());
		
		ret.setType((id >>> idMeta.getTypeBitsStartPos()) & idMeta.getTypeBitsMask());
		
		ret.setVersion((id >>> idMeta.getVersionBitsStartPos()) & idMeta.getVersionBitsMask());
		
		return ret;
	}

}
