/**
 * 
 */
package org.prelle.rpgframework.splittermond.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.prelle.splimo.Background;
import org.prelle.splimo.Culture;
import org.prelle.splimo.Education;
import org.prelle.splimo.Mastership;
import org.prelle.splimo.Power;
import org.prelle.splimo.Resource;
import org.prelle.splimo.Skill;
import org.prelle.splimo.Skill.SkillType;
import org.prelle.splimo.SkillSpecialization;
import org.prelle.splimo.Spell;
import org.prelle.splimo.SpellType;
import org.prelle.splimo.SplitterMondCore;
import org.prelle.splimo.creature.Creature;
import org.prelle.splimo.creature.CreatureFeatureType;
import org.prelle.splimo.creature.CreatureType;
import org.prelle.splimo.items.FeatureType;
import org.prelle.splimo.items.ItemTemplate;
import org.prelle.splimo.items.Material;

/**
 * @author prelle
 *
 */
public class IDExporter {

	//-------------------------------------------------------------------
	/**
	 */
	public IDExporter() {
		// TODO Auto-generated constructor stub
	}

	//-------------------------------------------------------------------
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SplittermondDataPlugin plugin = new SplittermondDataPlugin();
		plugin.init( (percent) -> {});
		
		generateMastershipsBySkills();
		generateSpells();
		generateSkills();
		generateCultures();
		generateBackgrounds();
		generateEducations();
		generatePowers();
		generateCreatureFeatureTypes();
		generateCreatureTypes();
		generateResources();
		generateSpellTypes();
		generateFeatureTypes();
		generateCreatures();
		generateEquipments();
		generateMaterials();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateMastershipsBySkills() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("masterships.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Meisterschaften nach Talenten</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Fertigkeit</th><th>Meisterschaft</th><th>ID</th><th>Quelle</th></tr>");
		int normal =0;
		int common = 0;
		for (Skill skill : SplitterMondCore.getSkills()) {
			int prev = normal;
			List<Mastership> list = skill.getMasterships();
			Collections.sort(list, new Comparator<Mastership>() {
				public int compare(Mastership o1, Mastership o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Mastership master : list) {
				if (master.isCommon()) common++; else normal++;
				out.println("    <tr><td>"+skill.getName()+"</td><td>"+master.getName()+"</td><td>"+skill.getId()+"/"+master.getId()+"</td><td>"+master.getProductNameShort()+" "+master.getPage()+"</td></tr>");
			}
			System.out.println((normal-prev)+" Meisterschaften für "+skill);
			
			List<SkillSpecialization> list2 = skill.getSpecializations();
			Collections.sort(list2, new Comparator<SkillSpecialization>() {
				public int compare(SkillSpecialization o1, SkillSpecialization o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			if (skill.getType()!=SkillType.COMBAT) {
				for (SkillSpecialization master : list2) {
					out.println("    <tr><td>"+skill.getName()+"</td><td>Schwerpunkt "+master.getName()+"</td><td>"+skill.getId()+"/"+master.getId()+"</td></tr>");
				}
			}
			out.println("    <tr><td colspan=\"3\">--</tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(normal+" Meisterschaften + "+common+" allgemeine");
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateSkills() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("skills.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Fertigkeiten</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Fertigkeit</th><th>ID</th><th>Quelle</th></tr>");
		List<Skill> list =SplitterMondCore.getSkills();
		Collections.sort(list);
		for (Skill data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateSpells() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("spells.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Zauber</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Zauber</th><th>ID</th><th>Quelle</th></tr>");
		List<Spell> list =SplitterMondCore.getSpells();
		Collections.sort(list, new Comparator<Spell>() {
			public int compare(Spell o1, Spell o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Spell master : list) {
			out.println("    <tr><td>"+master.getName()+"</td><td>"+master.getId()+"</td><td>"+master.getProductNameShort()+" "+master.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		
		System.out.println(list.size()+" Zauber");
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateResources() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("resources.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Resourcen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Ressource</th><th>ID</th><th>Quelle</th></tr>");
		List<Resource> list =SplitterMondCore.getResources();
		Collections.sort(list, new Comparator<Resource>() {
			public int compare(Resource o1, Resource o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Resource master : list) {
			out.println("    <tr><td>"+master.getName()+"</td><td>"+master.getId()+"</td><td>"+master.getProductNameShort()+" "+master.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateCultures() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("cultures.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Kulturen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Kultur</th><th>ID</th><th>Quelle</th></tr>");
		List<Culture> list =SplitterMondCore.getCultures();
		Collections.sort(list, new Comparator<Culture>() {
			public int compare(Culture o1, Culture o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Culture data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateBackgrounds() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("backgrounds.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Abstammungen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Abstammungen</th><th>ID</th><th>Quelle</th></tr>");
		List<Background> list =SplitterMondCore.getBackgrounds();
		Collections.sort(list, new Comparator<Background>() {
			public int compare(Background o1, Background o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Background data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateEducations() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("educations.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Ausbildungen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Ausbildung</th><th>Variante von</th><th>ID</th><th>Quelle</th></tr>");
		List<Education> list = new ArrayList<Education>();
		for (Education tmp : SplitterMondCore.getEducations()) {
			list.add(tmp);
			list.addAll(tmp.getVariants());
		}
		Collections.sort(list, new Comparator<Education>() {
			public int compare(Education o1, Education o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Education data : list) {
			Education variantOf = (data.getVariantOf()!=null)?(SplitterMondCore.getEducation(data.getVariantOf())):null;
			out.println("    <tr><td>"+data.getName()+"</td><td>"+((variantOf!=null)?variantOf.getName():"-")+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(list.size()+" Ausbildungen und Varianten");
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generatePowers() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("powers.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>St&auml;rken</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>St&auml;rke</th><th>ID</th><th>Quelle</th></tr>");
		List<Power> list =SplitterMondCore.getPowers();
		Collections.sort(list, new Comparator<Power>() {
			public int compare(Power o1, Power o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Power data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(list.size()+" Stärken");
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateCreatureFeatureTypes() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("creature_features_types.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Kreaturenmerkmale</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Merkmal</th><th>ID</th><th>Quelle</th></tr>");
		List<CreatureFeatureType> list =SplitterMondCore.getCreatureFeatureTypes();
		Collections.sort(list, new Comparator<CreatureFeatureType>() {
			public int compare(CreatureFeatureType o1, CreatureFeatureType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (CreatureFeatureType data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateCreatureTypes() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("creature_types.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Kreaturentypen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Art</th><th>ID</th><th>Quelle</th></tr>");
		List<CreatureType> list =SplitterMondCore.getCreatureTypes();
		Collections.sort(list, new Comparator<CreatureType>() {
			public int compare(CreatureType o1, CreatureType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (CreatureType data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateSpellTypes() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("spell_types.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Zaubertypen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Art</th><th>ID</th></tr>");
		List<SpellType> list = new ArrayList<SpellType>(Arrays.asList(SpellType.values()));
		Collections.sort(list, new Comparator<SpellType>() {
			public int compare(SpellType o1, SpellType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (SpellType data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.name()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException 
	 */
	private static void generateFeatureTypes() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("feature_types.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Waffernmerkamle</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Merkmal</th><th>ID</th><th>Quelle</th></tr>");
		List<FeatureType> list =SplitterMondCore.getFeatureTypes();
		Collections.sort(list, new Comparator<FeatureType>() {
			public int compare(FeatureType o1, FeatureType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (FeatureType data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(list.size() + " Kreaturen Merkmale");
	}

	//-------------------------------------------------------------------
	/**
	 * @throws IOException
	 * @since 17.04.2019 AS
	 */
	private static void generateCreatures() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("creature.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Kreaturen</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Kreatur</th><th>ID</th><th>Quelle</th></tr>");
		List<Creature> list =SplitterMondCore.getCreatures();
		Collections.sort(list, new Comparator<Creature>() {
			public int compare(Creature o1, Creature o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Creature data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(list.size() + " Kreaturen");
	}
	
	//-------------------------------------------------------------------
	/**
	 * @throws IOException
	 * @since 20.04.2019 AS
	 */
	private static void generateEquipments() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(new File("equipments.html")));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<html>");
		out.println(" <head>");
		out.println("   <title>Gegenstände</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println("   <table border=\"1\">");
		out.println("    <tr><th>Gegenstand</th><th>ID</th><th>Verfügbar</th><th>Preis</th><th>Last</th><th>Härte</th><th>Kompl.</th><th>Fertigkeit/Schwerpunkt</th><th>Material</th><th>Type</th><th>Quelle</th></tr>");
		List<ItemTemplate> list =SplitterMondCore.getItems();
		Collections.sort(list, new Comparator<ItemTemplate>() {
			public int compare(ItemTemplate o1, ItemTemplate o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (ItemTemplate data : list) {
			out.println("    <tr><td>"+data.getName()+"</td><td>"+data.getId()+"</td><td>"+data.getAvailability()+"</td><td>"+data.getPrice()+"</td><td>"+data.getLoad()+"</td><td>"+data.getRigidity()+"</td><td>"+data.getComplexity()+"</td><td>"+data.getSpecialization()+"</td><td>"+data.getMaterialType()+"</td><td>"+data.getFirstItemType()+"</td><td>"+data.getProductNameShort()+" "+data.getPage()+"</td></tr>");
		}
		out.println("  </table>");
		out.println(" </body>");
		out.flush();
		out.close();
		System.out.println(list.size()+" Waffen, Rüstungen und andere Ausrüstungsgegenstände");
	}

	//-------------------------------------------------------------------
		/**
		 * @throws IOException
		 * @since 15.10.2026 AS
		 */

		private static void generateMaterials() throws IOException {
			List<Material> list = SplitterMondCore.getMaterials();
			Collections.sort(list, new Comparator<Material>() {
				public int compare(Material o1, Material o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});

			// =================================================================
			// 1. BLOCK: HTML-Datei erzeugen
			// =================================================================
			PrintWriter out = new PrintWriter(new FileWriter(new File("materials.html")));
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<html>");
			out.println(" <head>");
			out.println("   <title>Materialien</title>");
			out.println(" </head>");
			out.println(" <body>");
			out.println("   <table border=\"1\">");
			out.println("    <tr><th>&nbsp;Material&nbsp;</th><th>&nbsp;ID&nbsp;</th><th style=\"width: 110px;\">&nbsp;Typ&nbsp;</th><th style=\"text-align: center;\">&nbsp;Qualität&nbsp;</th><th style=\"text-align: right;\">&nbsp;Preis&nbsp;</th><th>&nbsp;Quelle&nbsp;</th></tr>");
			
			for (Material data : list) {
				out.println("    <tr><td>&nbsp;"+data.getName()+"&nbsp;</td><td>&nbsp;"+data.getId()+"&nbsp;</td><td style=\"width: 110px;\">&nbsp;"+data.getType()+"&nbsp;</td><td style=\"text-align: center;\">&nbsp;"+data.getQuality()+"&nbsp;</td><td style=\"text-align: right;\">&nbsp;"+data.getPrice()+"&nbsp;</td><td>&nbsp;"+data.getProductNameShort()+" "+data.getPage()+"&nbsp;</td></tr>");
			}
			
			out.println("   </table>");
			out.println(" </body>");
			out.flush();
			out.close();

			// =================================================================
			// 2. BLOCK: CSV-Datei erzeugen (mit UTF-8, BOM und getrennter Quelle/Seite)
			// =================================================================
			java.io.BufferedWriter csvWriter = new java.io.BufferedWriter(
				new java.io.OutputStreamWriter(
					new java.io.FileOutputStream(new File("materials.csv")), 
					java.nio.charset.StandardCharsets.UTF_8
				)
			);
			// BOM für Excel hinzufügen
			csvWriter.write("\ufeff");
			csvWriter.write("Material;ID;Typ;Qualität;Preis;Quelle;Seite\n");

			for (Material data : list) {
				csvWriter.write(data.getName() + ";" + data.getId() + ";" + data.getType() + ";" + data.getQuality() + ";" + data.getPrice() + ";" + data.getProductNameShort() + ";" + data.getPage() + "\n");
			}
			
			csvWriter.flush();
			csvWriter.close();

			System.out.println(list.size()+"Besondere Materialien");
		}
}
