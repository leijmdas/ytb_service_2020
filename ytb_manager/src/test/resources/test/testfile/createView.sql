drop view vwProject_Stat_User_Area;
create view vwProject_Stat_User_Area as
select c.*, (select name from dict_area d where d.area_id = c.area_id) as name
from (select a.area_id, a.total_money1, b.total_money2
      from Project_Stat_User_Area a,
           Project_Stat_User_Area b
      where a.area_id = b.area_id
        and a.from_to = 1
        and b.from_to = 2
      union select a.area_id, a.total_money1, a.total_money2
            from Project_Stat_User_Area a
            where a.from_to = 1
              and a.area_id not in (select area_id from Project_Stat_User_Area where from_to = 2)
      union select b.area_id, b.total_money1, b.total_money2
            from Project_Stat_User_Area b
            where b.from_to = 2
              and b.area_id not in (select area_id from Project_Stat_User_Area where from_to = 1)) c
order by c.area_id;


location ^~ /m/ {
           alias C:\nginx-1.14.0\html\m;
		}