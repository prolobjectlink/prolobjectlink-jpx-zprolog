'org.logicware.DatabaseUser'(sa,'',nil,false,false,nil,nil).

'org.logicware.prolog.PrologDatabaseFunction'(fn,'','dat\hierarchical\test\functions.pl','org.logicware.util.ArrayList'(4,['X','Y','Z','R',nil,nil,nil,nil,nil,nil])).

'org.logicware.prolog.PrologDatabaseFunction'(fni,'','dat\hierarchical\test\functions.pl','org.logicware.util.ArrayList'(4,['X','Y','Z','R',nil,nil,nil,nil,nil,nil])).

'org.logicware.prolog.PrologDatabaseFunction'(fnj,'','dat\hierarchical\test\functions.pl','org.logicware.util.ArrayList'(4,['X','Y','Z','R',nil,nil,nil,nil,nil,nil])).

'org.logicware.prolog.PrologDatabaseFunction'(fnk,'','dat\hierarchical\test\functions.pl','org.logicware.util.ArrayList'(4,['X','Y','Z','R',nil,nil,nil,nil,nil,nil])).

'org.logicware.DatabaseClass'('Point','',false,'Point',false,'','org.logicware.util.ArrayList'(0,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil]),'org.logicware.util.HashMap'(3,[nil,nil,nil,nil,nil,nil,nil,nil,'org.logicware.util.HashMap$HashEntry'(x,'org.logicware.DatabaseField'(x,'',1,'java.lang.String',false,'Point.x',false,false,nil,nil,nil,nil,nil)),'org.logicware.util.HashMap$HashEntry'(y,'org.logicware.DatabaseField'(y,'',2,'java.lang.String',false,'Point.y',false,false,nil,nil,nil,nil,nil)),nil,'org.logicware.util.HashMap$HashEntry'(id,'org.logicware.DatabaseField'(id,'',0,'java.lang.Integer',false,'Point.id',true,false,nil,nil,nil,nil,nil)),nil,nil,nil,nil])).

'org.logicware.DatabaseClass'('Segment','',false,'Segment',false,'','org.logicware.util.ArrayList'(0,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil]),'org.logicware.util.HashMap'(3,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,'org.logicware.util.HashMap$HashEntry'(id,'org.logicware.DatabaseField'(id,'',0,'java.lang.Integer',false,'Segment.id',true,false,nil,nil,nil,nil,nil)),'org.logicware.util.HashMap$HashEntry'(poin0,'org.logicware.DatabaseField'(poin0,'',1,'org.logicware.domain.geometry.Point',false,'Segment.poin0',false,false,nil,nil,nil,nil,nil)),'org.logicware.util.HashMap$HashEntry'(poin1,'org.logicware.DatabaseField'(poin1,'',2,'org.logicware.domain.geometry.Point',false,'Segment.poin1',false,false,nil,nil,nil,nil,nil)),nil,nil])).

'org.logicware.DatabaseClass'('Polygon','',false,'Polygon',false,'','org.logicware.util.ArrayList'(0,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil]),'org.logicware.util.HashMap'(4,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,'org.logicware.util.HashMap$HashEntry'(id,'org.logicware.DatabaseField'(id,'',0,'java.lang.Integer',false,'Polygon.id',true,false,nil,nil,nil,nil,nil)),nil,'org.logicware.util.HashMap$HashEntry'(segment0,'org.logicware.DatabaseField'(segment0,'',1,'org.logicware.domain.geometry.Segment',false,'Polygon.segment0',false,false,nil,nil,nil,nil,nil)),'org.logicware.util.HashMap$HashEntry'(segment1,'org.logicware.DatabaseField'(segment1,'',2,'org.logicware.domain.geometry.Segment',false,'Polygon.segment1',false,false,nil,nil,nil,nil,nil)),'org.logicware.util.HashMap$HashEntry'(segment2,'org.logicware.DatabaseField'(segment2,'',3,'org.logicware.domain.geometry.Segment',false,'Polygon.segment2',false,false,nil,nil,nil,nil,nil))])).

'org.logicware.DatabaseClass'('Tetragon','',false,'Tetragon',false,'','org.logicware.util.ArrayList'(0,[nil,nil,nil,nil,nil,nil,nil,nil,nil,nil]),'org.logicware.util.HashMap'(1,['org.logicware.util.HashMap$HashEntry'(segment3,'org.logicware.DatabaseField'(segment3,'',0,'org.logicware.domain.geometry.Segment',false,'Tetragon.segment3',false,false,nil,nil,nil,nil,nil)),nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil,nil])).

'org.logicware.DatabaseSequence'(0,'org.logicware.domain.geometry.Point',point_sequence,1).

'org.logicware.DatabaseSequence'(0,'org.logicware.domain.geometry.Segment',segment_sequence,1).

'org.logicware.DatabaseSequence'(0,'org.logicware.domain.geometry.Polygon',polygon_sequence,1).

'org.logicware.DatabaseSequence'(0,'org.logicware.domain.geometry.Tetragon',tetragon_sequence,1).

'org.logicware.prolog.PrologDatabaseView'('org.logicware.domain.geometry.view.SamePoint','','dat\hierarchical\test\views.pl','org.logicware.util.ArrayList'(3,['Idp','X','Y',nil,nil,nil,nil,nil,nil,nil])).

