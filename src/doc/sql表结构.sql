-- 1、角色表
create table SYSROLE
(
  ROLEID   VARCHAR2(20) primary key,
  ROLENAME VARCHAR2(50)
);
-- Add comments to the table 
comment on table SYSROLE
  is '角色表';
-- Add comments to the columns 
comment on column SYSROLE.ROLEID
  is '角色ID';
comment on column SYSROLE.ROLENAME
  is '角色名称';
insert into sysrole (ROLEID, ROLENAME)
values ('admin', '管理员');
insert into sysrole (ROLEID, ROLENAME)
values ('user', '用户');
insert into sysrole (ROLEID, ROLENAME)
values ('test', '测试');

-- 2、用户信息表
create table SYSUSER
(
  USERID   VARCHAR2(50) primary key,
  USERNAME VARCHAR2(50),
  PASSWD   VARCHAR2(50),
  USERQQ   NUMBER(20),
  REDATE   TIMESTAMP(6)
);
-- Add comments to the table 
comment on table SYSUSER
  is '用户信息表';
-- Add comments to the columns 
comment on column SYSUSER.USERID
  is '用户ID';
comment on column SYSUSER.USERNAME
  is '用户名';
comment on column SYSUSER.PASSWD
  is '密码';
comment on column SYSUSER.userqq
  is 'QQ';
comment on column SYSUSER.redate
  is '注册时间';
  
-- 3、用户角色表
create table USERROLE
(
  URID     NUMBER(16) primary key,
  ROLEID VARCHAR2(20),
  USERID VARCHAR2(50)
);
-- Add comments to the table 
comment on table USERROLE
  is '用户角色表';
-- Add comments to the columns 
comment on column USERROLE.URID
  is 'ID';
comment on column USERROLE.ROLEID
  is '角色ID';
comment on column USERROLE.USERID
  is '用户ID';

-- 4、系统菜单表
create table SYSMENU
(
  MENUID   NUMBER(16) primary key,
  MENUCODE VARCHAR2(50),
  MENUNAME VARCHAR2(50),
  MENUURL  VARCHAR2(255),
  MENUPID  NUMBER(16)
);
-- Add comments to the table 
comment on table SYSMENU
  is '系统菜单表';
-- Add comments to the columns 
comment on column SYSMENU.MENUID
  is '菜单ID';
comment on column SYSMENU.MENUNAME
  is '菜单名称';
comment on column SYSMENU.MENUCODE
  is '菜单代码';
comment on column SYSMENU.MENUURL
  is '菜单路径';
comment on column SYSMENU.MENUPID
  is '父级菜单';

-- 5、权限操作表
create table SYSPRIVILEGE
(
  PRIVILEGECODE VARCHAR2(20) primary key,
  PRIVILEGENAME VARCHAR2(20)
);
-- Add comments to the table 
comment on table SYSPRIVILEGE
  is '权限操作表';
-- Add comments to the columns 
comment on column SYSPRIVILEGE.PRIVILEGECODE
  is '权限操作代码';
comment on column SYSPRIVILEGE.PRIVILEGENAME
  is '权限操作名称';

-- 6、角色权限表
create table ROLEMENU
(
  RMID          NUMBER(16) not null,
  ROLEID        VARCHAR2(20),
  MENUID        NUMBER(16),
  PRIVILEGECODE VARCHAR2(20)
);
-- Add comments to the table 
comment on table ROLEMENU
  is '角色权限表';
-- Add comments to the columns 
comment on column ROLEMENU.RMID
  is '角色权限ID';
comment on column ROLEMENU.ROLEID
  is '角色ID';
comment on column ROLEMENU.MENUID
  is '菜单ID';
comment on column ROLEMENU.PRIVILEGECODE
  is '权限操作代码';