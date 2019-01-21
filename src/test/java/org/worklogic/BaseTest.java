package org.worklogic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.logicware.prolog.Prolog;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.zprolog.ZProlog;
import org.logicware.prolog.zprolog.ZPrologContainerFactory;
import org.worklogic.db.ContainerFactory;
import org.worklogic.db.DatabaseClass;
import org.worklogic.db.DatabaseUser;
import org.worklogic.db.HierarchicalCache;
import org.worklogic.db.HierarchicalDatabase;
import org.worklogic.db.RelationalDatabase;
import org.worklogic.db.Schema;
import org.worklogic.db.Storage;
import org.worklogic.db.StorageManager;
import org.worklogic.db.StorageMode;
import org.worklogic.db.StoragePool;
import org.worklogic.db.etc.Settings;
import org.worklogic.domain.geometry.Point;
import org.worklogic.domain.geometry.Polygon;
import org.worklogic.domain.geometry.Segment;
import org.worklogic.domain.geometry.Tetragon;
import org.worklogic.domain.geometry.view.SamePoint;
import org.worklogic.domain.model.Address;
import org.worklogic.domain.model.Department;
import org.worklogic.domain.model.Employee;
import org.worklogic.domain.model.Person;
import org.worklogic.domain.model.view.AnEmployeeView;

/** @author Jose Zalacain @since 1.0 */
public abstract class BaseTest {

	protected Settings settings;

	protected Storage storage;
	protected StoragePool storagePool;
	protected HierarchicalCache cache;
	protected StorageManager storageManager;

	protected DatabaseUser user;

	protected RelationalDatabase rdb;
	protected HierarchicalDatabase hdb;

	protected Schema rschema;
	protected Schema hschema;

	// file system separator
	protected final static char SEPARATOR = File.separatorChar;

	protected static final String LOCATION = "data-test";
	protected static final String POOL_ROOT = "pool-test";
	protected static final String POOL_NAME = "pool-name";
	protected static final String ROOT = "data" + SEPARATOR + "test";
	protected static final String BASE_LOCATION = "data" + SEPARATOR + "test" + SEPARATOR + "org" + SEPARATOR
			+ "logicware" + SEPARATOR + "domain" + SEPARATOR + "geometry";

	// File Backup Names Constants
	protected static final String BACKUP_ZIP_FILE_NAME_1 = "data.zip";
	protected static final String BACKUP_ZIP_FILE_NAME_2 = "data-test.zip";
	protected static final String BACKUP_ZIP_FILE_NAME_3 = "pool-test.zip";
	protected static final String BACKUP_ZIP_FILE_NAME_4 = "hierarchy-test.zip";
	protected static final String BACKUP_DIRECTORY = "backup" + SEPARATOR;
	protected static final String BACKUP_ZIP_FILE_PATH_1 = BACKUP_DIRECTORY + BACKUP_ZIP_FILE_NAME_1;
	protected static final String BACKUP_ZIP_FILE_PATH_2 = BACKUP_DIRECTORY + BACKUP_ZIP_FILE_NAME_2;
	protected static final String BACKUP_ZIP_FILE_PATH_3 = BACKUP_DIRECTORY + BACKUP_ZIP_FILE_NAME_3;
	protected static final String BACKUP_ZIP_FILE_PATH_4 = BACKUP_DIRECTORY + BACKUP_ZIP_FILE_NAME_4;

	protected static final Class<? extends ContainerFactory> driver = ZPrologContainerFactory.class;
	protected static final PrologProvider provider = Prolog.newProvider(ZProlog.class);

	protected static final PrologInteger zero = provider.newInteger(0);
	protected static final PrologInteger one = provider.newInteger(1);
	protected static final PrologInteger two = provider.newInteger(2);
	protected static final PrologInteger three = provider.newInteger(3);
	protected static final PrologInteger four = provider.newInteger(4);
	protected static final PrologInteger five = provider.newInteger(5);
	protected static final PrologInteger six = provider.newInteger(6);
	protected static final PrologInteger seven = provider.newInteger(7);
	protected static final PrologInteger eight = provider.newInteger(8);
	protected static final PrologInteger nine = provider.newInteger(9);
	protected static final PrologInteger ten = provider.newInteger(10);

	protected static final Point a = new Point("a", 3, 14);
	protected static final Point b = new Point("b", 3, 14);
	protected static final Point c = new Point("c", 3, 14);
	protected static final Point d = new Point("d", 3, 14);

	protected static final Segment ab = new Segment("ab", a, b);
	protected static final Segment bc = new Segment("bc", b, c);
	protected static final Segment ca = new Segment("ca", c, a);
	protected static final Segment cd = new Segment("cd", c, d);
	protected static final Segment da = new Segment("da", d, a);

	protected static final Polygon triangle = new Polygon(new String("triangle"), ab, bc, ca);
	protected static final Polygon tetragon = new Tetragon(new String("tetragon"), ab, bc, cd, da);

	@Before
	public void setUp() throws Exception {

		settings = new Settings(driver);
		cache = settings.createHierarchicalCache();
		storage = settings.createStorage(LOCATION);
		storageManager = settings.createStorageManager(ROOT, StorageMode.STORAGE_POOL);
		storagePool = settings.createStoragePool(POOL_ROOT, POOL_NAME);

		user = new DatabaseUser("sa", "");

		rdb = settings.createRelationalDatabase(StorageMode.STORAGE_POOL, "test", user);
		hdb = settings.createHierarchicalDatabase(StorageMode.STORAGE_POOL, "test", user);

		// Relational
		rschema = settings.createRelationalDatabase(StorageMode.STORAGE_POOL, "test", user).getSchema();

		DatabaseClass address = rschema.addClass("Address", "");
		address.addField("street", "", 0, String.class);
		address.addField("city", "", 1, String.class);
		address.addField("state", "", 2, String.class);
		address.addField("zip", "", 3, String.class);
		address.addField("country", "", 4, String.class);
		rschema.addSequence("adddress_sequence", "", Address.class, 1);

		DatabaseClass person = rschema.addClass("Person", "");
		person.addField("id", "", 0, Integer.class, true);
		person.addField("firstName", "", 1, String.class);
		person.addField("middleName", "", 2, String.class);
		person.addField("lastName", "", 3, String.class);
		person.addField("address", "", 4, Address.class);
		person.addField("phones", "", 5, List.class, String.class);
		person.addField("emails", "", 6, Collection.class, String.class);
		person.addField("nickNames", "", 7, Set.class, String.class);
		person.addField("birthDate", "", 8, Date.class);
		person.addField("joinDate", "", 9, Date.class);
		person.addField("lastLoginDate", "", 10, Date.class);
		person.addField("loginCount", "", 11, Integer.class);
		rschema.addSequence("person_sequence", "", Person.class, 1);

		DatabaseClass employee = rschema.addClass("Employee", "", person);
		employee.addField("salary", "", 0, Long.class);
		employee.addField("department", "", 1, Department.class);
		rschema.addSequence("employee_sequence", "", Employee.class, 1);

		DatabaseClass department = rschema.addClass("Department", "");
		department.addField("id", "", 0, Integer.class, true);
		department.addField("name", "", 1, String.class);
		department.addField("employeesByCubicle", "", 2, Map.class, Employee.class);
		rschema.addSequence("department_sequence", "", Department.class, 1);

		rschema.addFunction("fn", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		rschema.addFunction("fni", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		rschema.addFunction("fnj", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		rschema.addFunction("fnk", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");

		rschema.addView(AnEmployeeView.class, "")
				.addInstructions("findall(Id/X/Y,'" + Employee.class.getName() + "'(Id,X,Y),L)");

		// Hierarchical
		hschema = settings.createHierarchicalDatabase(StorageMode.STORAGE_POOL, "test", user).getSchema();

		DatabaseClass point = hschema.addClass("Point", "");
		point.addField("id", "", 0, Integer.class, true);
		point.addField("x", "", 1, String.class);
		point.addField("y", "", 2, String.class);
		hschema.addSequence("point_sequence", "", Point.class, 1);

		DatabaseClass segment = hschema.addClass("Segment", "");
		segment.addField("id", "", 0, Integer.class, true);
		segment.addField("poin0", "", 1, Point.class);
		segment.addField("poin1", "", 2, Point.class);
		hschema.addSequence("segment_sequence", "", Segment.class, 1);

		DatabaseClass polygon = hschema.addClass("Polygon", "");
		polygon.addField("id", "", 0, Integer.class, true);
		polygon.addField("segment0", "", 1, Segment.class);
		polygon.addField("segment1", "", 2, Segment.class);
		polygon.addField("segment2", "", 3, Segment.class);
		hschema.addSequence("polygon_sequence", "", Polygon.class, 1);

		DatabaseClass tetragon = hschema.addClass("Tetragon", "", polygon);
		tetragon.addField("segment3", "", 0, Segment.class);
		hschema.addSequence("tetragon_sequence", "", Tetragon.class, 1);

		hschema.addFunction("fn", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		hschema.addFunction("fni", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		hschema.addFunction("fnj", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");
		hschema.addFunction("fnk", "").addParameter("X").addParameter("Y").addParameter("Z").addParameter("R")
				.addInstructions("R is X*Y*Z");

		hschema.addView(SamePoint.class, "")
				.addInstructions("findall(Id/X/Y,'" + Point.class.getName() + "'(Id,X,Y),L)");

	}

	@After
	public void tearDown() throws Exception {

	}

	protected <S> List<S> createList(List<Object> solutions) {
		List<S> list = new ArrayList<S>();
		for (Object solution : solutions) {
			if (solution instanceof Object[]) {
				Object[] objects = (Object[]) solution;
				if (objects.length > 0) {
					Object object = objects[0];
					S s = (S) object;
					list.add(s);
				}
			}
		}
		return list;
	}

}
