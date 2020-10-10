package novel.mysql;


import novel.INovel;
import novel.entity.NovelAttribute;

/**
 * @author : zhangshuai
 * @date : 14:03 2020/9/30
 */
public class NovelImpl implements INovel {
    public int SaveAttribute(NovelAttribute attribute) {
       // NovelDao novelDao = new NovelDao();
        StringBuffer sql = new StringBuffer();
        sql.append("insert into novel (`id`,`FictionName`,`FictionChapter`,`FictionUrl`)")
                .append("VALUES (? , ? , ? , ?)");

//        List<String> sqlValues = new ArrayList<String>();
//        sqlValues.add(attribute.getId());
//        sqlValues.add("" + attribute.getFictionName());
//        sqlValues.add("" + attribute.getFictionChapter());
//        sqlValues.add("" + attribute.getFictionUrl());
        //System.out.println(attribute.toString());
//        int result = novelDao.executeUpdate(sql.toString(), sqlValues);
//        return result;
        return 1;
    }
}
