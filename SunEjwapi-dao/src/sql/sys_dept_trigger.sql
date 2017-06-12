DELIMITER ;;
/* 上午10:33:01 local standard_ejingwu */ 
CREATE TRIGGER `validate_code` BEFORE INSERT ON `sys_dept` FOR EACH ROW    if LENGTH(new.code) % 3 > 0 then       delete from sys_dept where code = new.code;   end if;;
/* 上午10:34:37 local standard_ejingwu */ 
CREATE TRIGGER `validate_code_update` BEFORE UPDATE ON `sys_dept` FOR EACH ROW if LENGTH(new.code) % 3 > 0 then       delete from sys_dept where code = new.code;   end if;;
DELIMITER ;