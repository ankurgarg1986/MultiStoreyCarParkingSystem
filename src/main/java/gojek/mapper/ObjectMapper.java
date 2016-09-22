package gojek.mapper;

/**
 * 
 * Basic Mapper for converting One object to another.
 * Can be used across the codebase.
 * @author agarg
 *
 */
public interface ObjectMapper<o1,o2> {

	/**
	 * This parameter converts Object o1 to Object o2
	 * @param o1
	 * @param o2
	 * @return Object o2
	 */
	public Object convertObjects(Object o1,Object o2);
	
}
