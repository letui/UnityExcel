# UnityExcel
操纵Excel的好工具，支持集合类型直接导出excel。
<h3>用例</h3>
<pre>
List<DemoObject> temp=new ArrayList<DemoObject>();
temp.add(new DemoObject("宋丹丹",32,33.500,new Date()));
temp.add(new DemoObject("赵本山",36,23.5,new Date()));
Unity u=new Unity97();
try {
  u.exportUTable(temp, new FileOutputStream("src/temp.xls")).close();
} catch (IOException e) {
  e.printStackTrace();
}
</pre>
<h3>POJO</h3>
<pre>
class DemoObject {
	@UColumn(Head="姓名",Index=1)
	private String name;
	@UColumn(Head="年龄",Index=2)
	@UMapper(JsonMap="{'1':'一','2':'二'}")
	private int age;
	@UColumn(Head="腰围",Index=3,Suffix="寸",Prefix="SN-")
	@UFormatter(FormatPartten="##.##")
	private double width;
	@UColumn(Index=4)
	@UFormatter(FormatPartten="yyyy-MM-dd")
	@UStyleBinder()
	private Date birthday;
 }
</pre>
看懂或者看不懂，代码就在这里，谢谢。
