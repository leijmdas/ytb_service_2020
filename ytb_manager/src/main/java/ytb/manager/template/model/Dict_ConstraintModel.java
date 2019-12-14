/*****************************************************************************************************
Author		:
Date		:2018/8/1
Description	:
Update		:
Author			Date				Description			
*****************************************************************************************************/

package ytb.manager.template.model;


/// <summary>
/// db.ytb_dict.model
/// </summary>
public class Dict_ConstraintModel
{
	private  int  ConstraintId  ;
	private  int  WorkJobTypeId  ;
	private  String  Title  ;
	private  int  IsValid  ;


	/* 
	 * 真值约束Id
	 */
	public  int getConstraintId () 
	{ 
		return this.ConstraintId ;
	} 
	public void setConstraintId ( int  ConstraintId) 
	{ 
		this.ConstraintId = ConstraintId ;
	} 

	/* 
	 * 岗位类别Id
	 */
	public  int getWorkJobTypeId () 
	{ 
		return this.WorkJobTypeId ;
	} 
	public void setWorkJobTypeId ( int  WorkJobTypeId) 
	{ 
		this.WorkJobTypeId = WorkJobTypeId ;
	} 

	/* 
	 * 模板表ID
	 */
	public  String getTitle () 
	{ 
		return this.Title ;
	} 
	public void setTitle ( String  Title) 
	{ 
		this.Title = Title ;
	} 

	/* 
	 * 是否有效
	 */
	public  int getIsValid () 
	{ 
		return this.IsValid ;
	} 
	public void setIsValid ( int  IsValid) 
	{ 
		this.IsValid = IsValid ;
	} 


	/// <summary>
	/// 构造函数
	/// </summary>
	public Dict_ConstraintModel(){;}
}
