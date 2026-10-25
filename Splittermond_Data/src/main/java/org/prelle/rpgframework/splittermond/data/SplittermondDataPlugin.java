package org.prelle.rpgframework.splittermond.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.prelle.splimo.BasePluginData;
import org.prelle.splimo.SpliMoCharacter;
import org.prelle.splimo.SplitterMondCore;

import de.rpgframework.ConfigContainer;
import de.rpgframework.ConfigOption;
import de.rpgframework.character.RulePlugin;
import de.rpgframework.character.RulePluginFeatures;
import de.rpgframework.core.CommandResult;
import de.rpgframework.core.CommandType;
import de.rpgframework.core.CustomDataHandler;
import de.rpgframework.core.CustomDataHandler.CustomDataPackage;
import de.rpgframework.core.CustomDataHandlerLoader;
import de.rpgframework.core.RoleplayingSystem;

/**
 * @author Stefan
 *
 */
public class SplittermondDataPlugin implements RulePlugin<SpliMoCharacter> {

	private static Logger logger = LogManager.getLogger("splittermond.data");

	private static boolean alreadyInitialized = false;

	//--------------------------------------------------------------------
	public SplittermondDataPlugin() {
	}

	//--------------------------------------------------------------------
	public static InputStream loadImage(String fname) {
		return SplittermondDataPlugin.class.getResourceAsStream(fname);
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getID()
	 */
	@Override
	public String getID() {
		return "Data";
	}

	//-------------------------------------------------------------------
	/**
	 * @see de.rpgframework.core.CommandBusListener#getReadableName()
	 */
	@Override
	public String getReadableName() {
		if (this.getClass().getPackage().getImplementationTitle()!=null)
			return this.getClass().getPackage().getImplementationTitle();
		return "Data";
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getRules()
	 */
	@Override
	public RoleplayingSystem getRules() {
		return RoleplayingSystem.SPLITTERMOND;
	}

	//-------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getRequiredPlugins()
	 */
	@Override
	public Collection<String> getRequiredPlugins() {
		return Arrays.asList("CORE");
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getSupportedFeatures()
	 */
	@Override
	public Collection<RulePluginFeatures> getSupportedFeatures() {
		return Arrays.asList(new RulePluginFeatures[]{RulePluginFeatures.DATA});
	}

	//-------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#attachConfigurationTree(de.rpgframework.ConfigContainer)
	 */
	@Override
	public void attachConfigurationTree(ConfigContainer addBelow) {
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getConfiguration()
	 */
	@Override
	public List<ConfigOption<?>> getConfiguration() {
		return null;
	}

	//--------------------------------------------------------------------
	public void initCoreOnly(RulePluginProgessListener callback) {
		if (alreadyInitialized)
			return;
		double totalPlugins = 23.0;
		double count = 0;
		alreadyInitialized = true;
		logger.info("START -------------------------------Core-----------------------------------------------");
		PluginSkeleton CORE = new PluginSkeleton("CORE", "Splittermond Core Rules");
		Class<SplittermondDataPlugin> clazz = SplittermondDataPlugin.class;
		SplitterMondCore.loadPowers(CORE, clazz.getResourceAsStream("core/data/powers.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadSkills(CORE, clazz.getResourceAsStream("core/data/skills.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadMasterships(CORE, clazz.getResourceAsStream("core/data/masterships.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadSpells(CORE, clazz.getResourceAsStream("core/data/spells.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadResources(CORE, clazz.getResourceAsStream("core/data/resources.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadLanguages(CORE, clazz.getResourceAsStream("core/data/languages.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCultureLores(CORE, clazz.getResourceAsStream("core/data/culturelores.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadBackgrounds(CORE, clazz.getResourceAsStream("core/data/backgrounds.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadRaces(CORE, clazz.getResourceAsStream("core/data/races.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCultures(CORE, clazz.getResourceAsStream("core/data/cultures.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEducations(CORE, clazz.getResourceAsStream("core/data/educations.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadFeatureTypes(CORE, clazz.getResourceAsStream("core/data/featuretypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadMaterials(CORE, clazz.getResourceAsStream("core/data/materials.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEquipment(CORE, clazz.getResourceAsStream("core/data/equipment.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEnhancements(CORE, clazz.getResourceAsStream("core/data/enhancements.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatureTypes(CORE, clazz.getResourceAsStream("core/data/creaturetypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatureFeatureTypes(CORE, clazz.getResourceAsStream("core/data/creaturefeaturetypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatures(CORE, clazz.getResourceAsStream("core/data/creatures.xml"), CORE.getResources(), CORE.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------World-----------------------------------------------");
		PluginSkeleton WORLD = new PluginSkeleton("World", "Splittermond - Die Welt");
		SplitterMondCore.loadCultureLores(WORLD, clazz.getResourceAsStream("world/data/culturelores-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadLanguages(WORLD, clazz.getResourceAsStream("world/data/languages-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadCultures(WORLD, clazz.getResourceAsStream("world/data/cultures-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadTowns(WORLD, clazz.getResourceAsStream("world/data/towns-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		callback.progressChanged(25.0);
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Fahrende Völker-----------------------------------");
		PluginSkeleton FAHREND = new PluginSkeleton("FahrendeVoelker", "Fahrende Völker");
		SplitterMondCore.loadMasterships(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/masterships-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadCultures(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/cultures-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadEquipment(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/equipment-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadEducations(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/educations-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadNameTable(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/nametable-teleshai.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadSpells(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/spells-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadCreatures(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/creatures-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );
	}
	
	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#init()
	 */
	@Override
	public void init(RulePluginProgessListener callback) {
		if (alreadyInitialized)
			return;
		double totalPlugins = 25.0;
		double count = 0;
		alreadyInitialized = true;
		logger.info("START -------------------------------Core-----------------------------------------------");
		PluginSkeleton CORE = new PluginSkeleton("CORE", "Splittermond Core Rules");
		Class<SplittermondDataPlugin> clazz = SplittermondDataPlugin.class;
		SplitterMondCore.loadPowers(CORE, clazz.getResourceAsStream("core/data/powers.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadSkills(CORE, clazz.getResourceAsStream("core/data/skills.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadMasterships(CORE, clazz.getResourceAsStream("core/data/masterships.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadSpells(CORE, clazz.getResourceAsStream("core/data/spells.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadResources(CORE, clazz.getResourceAsStream("core/data/resources.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadLanguages(CORE, clazz.getResourceAsStream("core/data/languages.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCultureLores(CORE, clazz.getResourceAsStream("core/data/culturelores.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadBackgrounds(CORE, clazz.getResourceAsStream("core/data/backgrounds.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadRaces(CORE, clazz.getResourceAsStream("core/data/races.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCultures(CORE, clazz.getResourceAsStream("core/data/cultures.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEducations(CORE, clazz.getResourceAsStream("core/data/educations.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadFeatureTypes(CORE, clazz.getResourceAsStream("core/data/featuretypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadMaterials(CORE, clazz.getResourceAsStream("core/data/materials.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEquipment(CORE, clazz.getResourceAsStream("core/data/equipment.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadEnhancements(CORE, clazz.getResourceAsStream("core/data/enhancements.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatureTypes(CORE, clazz.getResourceAsStream("core/data/creaturetypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatureFeatureTypes(CORE, clazz.getResourceAsStream("core/data/creaturefeaturetypes.xml"), CORE.getResources(), CORE.getHelpResources());
		SplitterMondCore.loadCreatures(CORE, clazz.getResourceAsStream("core/data/creatures.xml"), CORE.getResources(), CORE.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------World-----------------------------------------------");
		PluginSkeleton WORLD = new PluginSkeleton("World", "Splittermond - Die Welt");
		SplitterMondCore.loadCultureLores(WORLD, clazz.getResourceAsStream("world/data/culturelores-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadLanguages(WORLD, clazz.getResourceAsStream("world/data/languages-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadCultures(WORLD, clazz.getResourceAsStream("world/data/cultures-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		SplitterMondCore.loadTowns(WORLD, clazz.getResourceAsStream("world/data/towns-world.xml"), WORLD.getResources(), WORLD.getHelpResources());
		callback.progressChanged(25.0);
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Mondstahlklingen------------------------------------");
		PluginSkeleton MSK = new PluginSkeleton("MSK", "Mondstahlklingen");
		SplitterMondCore.loadFeatureTypes(MSK, clazz.getResourceAsStream("msk/data/featuretypes-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadMaterials(MSK, clazz.getResourceAsStream("msk/data/materials-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEnhancements(MSK, clazz.getResourceAsStream("msk/data/enhancements-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadPersonalizations(MSK, clazz.getResourceAsStream("msk/data/personalizations-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/handgemenge.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/hiebwaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/kettenwaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/klingenwaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/stangenwaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/schusswaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/wurfwaffen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/ruestungen.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/schilde.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/projectiles.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/weaponitems.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/container.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/tools.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/animals.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/clothing.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/shady.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/light.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/travel.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/healing.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/alchemy.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/writing.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/climbing.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/cosmetics.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadEquipment(MSK, clazz.getResourceAsStream("msk/data/recreation.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadSpells   (MSK, clazz.getResourceAsStream("msk/data/spells-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		SplitterMondCore.loadMasterships(MSK, clazz.getResourceAsStream("msk/data/skills-msk.xml"), MSK.getResources(), MSK.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------BuU-----------------------------------------------");
		PluginSkeleton BUU = new PluginSkeleton("BuU", "Bestien und Ungeheuer");
		SplitterMondCore.loadCreatureTypes(BUU, clazz.getResourceAsStream("buu/data/creaturetypes-buu.xml"), BUU.getResources(), BUU.getHelpResources());
		SplitterMondCore.loadCreatureFeatureTypes(BUU, clazz.getResourceAsStream("buu/data/creaturefeaturetypes-buu.xml"), BUU.getResources(), BUU.getHelpResources());
		SplitterMondCore.loadCreatures(BUU, clazz.getResourceAsStream("buu/data/creatures-buu.xml"), BUU.getResources(), BUU.getHelpResources());
		SplitterMondCore.loadMaterials(BUU, clazz.getResourceAsStream("buu/data/materials-buu.xml"), BUU.getResources(), BUU.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Beastmaster---------------------------------------");
		PluginSkeleton BEAST = new PluginSkeleton("Beastmaster", "Bestienmeister");
		SplitterMondCore.loadMasterships(BEAST, clazz.getResourceAsStream("beastmaster/data/masterships-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadSpells(BEAST, clazz.getResourceAsStream("beastmaster/data/spells-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadCreatureTypes(BEAST, clazz.getResourceAsStream("beastmaster/data/creaturetypes-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadCreatureModules(BEAST, clazz.getResourceAsStream("beastmaster/data/creaturemodules-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadCreatures(BEAST, clazz.getResourceAsStream("beastmaster/data/creatures-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadCreatureModules(BEAST, clazz.getResourceAsStream("beastmaster/data/creaturetrainings-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadEducations(BEAST, clazz.getResourceAsStream("beastmaster/data/educations-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		SplitterMondCore.loadEquipment(BEAST, clazz.getResourceAsStream("beastmaster/data/equipment-beastmaster.xml"), BEAST.getResources(), BEAST.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Esmoda--------------------------------------------");
		PluginSkeleton ESMODA = new PluginSkeleton("Esmoda", "Esmoda");
		SplitterMondCore.loadEquipment(ESMODA, clazz.getResourceAsStream("esmoda/data/alchemy-esmoda.xml"), ESMODA.getResources(), ESMODA.getHelpResources());
		SplitterMondCore.loadEquipment(ESMODA, clazz.getResourceAsStream("esmoda/data/equipment-esmoda.xml"), ESMODA.getResources(), ESMODA.getHelpResources());
		SplitterMondCore.loadEducations(ESMODA, clazz.getResourceAsStream("esmoda/data/educations-esmoda.xml"), ESMODA.getResources(), ESMODA.getHelpResources());
		SplitterMondCore.loadMaterials(ESMODA, clazz.getResourceAsStream("esmoda/data/materials-esmoda.xml"), ESMODA.getResources(), ESMODA.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Fahrende Völker-----------------------------------");
		PluginSkeleton FAHREND = new PluginSkeleton("FahrendeVoelker", "Fahrende Völker");
		SplitterMondCore.loadMasterships(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/masterships-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadCultures(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/cultures-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadEquipment(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/equipment-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadEducations(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/educations-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadNameTable(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/nametable-teleshai.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadSpells(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/spells-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		SplitterMondCore.loadCreatures(FAHREND, clazz.getResourceAsStream("fahrendevoelker/data/creatures-fahrendevoelker.xml"), FAHREND.getResources(), FAHREND.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Farukan-------------------------------------------");
		PluginSkeleton FARUKAN = new PluginSkeleton("Farukan", "Farukan");
		SplitterMondCore.loadMasterships(FARUKAN, clazz.getResourceAsStream("farukan/data/masterships-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
		SplitterMondCore.loadSpells(FARUKAN, clazz.getResourceAsStream("farukan/data/spells-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
    	SplitterMondCore.loadEquipment(FARUKAN, clazz.getResourceAsStream("farukan/data/equipment-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
		SplitterMondCore.loadEducations(FARUKAN, clazz.getResourceAsStream("farukan/data/educations-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
		SplitterMondCore.loadNameTable(FARUKAN, clazz.getResourceAsStream("farukan/data/nametable-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
		SplitterMondCore.loadMaterials(FARUKAN, clazz.getResourceAsStream("farukan/data/materials-farukan.xml"), FARUKAN.getResources(), FARUKAN.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Flammensenke--------------------------------------");
		PluginSkeleton FLAMMEN = new PluginSkeleton("Flammensenke", "Flammensenke");
		SplitterMondCore.loadEquipment(FLAMMEN, clazz.getResourceAsStream("flammensenke/data/equipment-flammensenke.xml"), FLAMMEN.getResources(), FLAMMEN.getHelpResources());
		SplitterMondCore.loadEducations(FLAMMEN, clazz.getResourceAsStream("flammensenke/data/educations-flammensenke.xml"), FLAMMEN.getResources(), FLAMMEN.getHelpResources());
		SplitterMondCore.loadMaterials(FLAMMEN, clazz.getResourceAsStream("flammensenke/data/materials-flammensenke.xml"), FLAMMEN.getResources(), FLAMMEN.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START ----------------------------Banden und Orden-------------------------------------");
		PluginSkeleton BUO = new PluginSkeleton("BuO", "Banden und Orden");
		SplitterMondCore.loadCreatures(BUO, clazz.getResourceAsStream("buo/data/creatures-buo.xml"), BUO.getResources(), BUO.getHelpResources());
		SplitterMondCore.loadEducations(BUO, clazz.getResourceAsStream("buo/data/educations-buo.xml"), BUO.getResources(), BUO.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );		
		
		logger.info("START -------------------------------Selenia-------------------------------------------");
		PluginSkeleton SELENIA = new PluginSkeleton("Selenia", "Selenia");
		SplitterMondCore.loadMasterships(SELENIA, clazz.getResourceAsStream("selenia/data/masterships-selenia.xml"), SELENIA.getResources(), SELENIA.getHelpResources());
		SplitterMondCore.loadEquipment(SELENIA, clazz.getResourceAsStream("selenia/data/equipment-selenia.xml"), SELENIA.getResources(), SELENIA.getHelpResources());
		SplitterMondCore.loadEducations(SELENIA, clazz.getResourceAsStream("selenia/data/educations-selenia.xml"), SELENIA.getResources(), SELENIA.getHelpResources());
		SplitterMondCore.loadNameTable(SELENIA, clazz.getResourceAsStream("selenia/data/nametable-selenia.xml"), SELENIA.getResources(), SELENIA.getHelpResources());
		SplitterMondCore.loadFeatureTypes(SELENIA, clazz.getResourceAsStream("selenia/data/featuretypes-selenia.xml"), SELENIA.getResources(), SELENIA.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Götter--------------------------------------------");
		PluginSkeleton GOETTER = new PluginSkeleton("GOETTER", "Die Götter");
		SplitterMondCore.loadResources(GOETTER, clazz.getResourceAsStream("goetter/data/resources-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadPowers(GOETTER, clazz.getResourceAsStream("goetter/data/powers-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadMasterships(GOETTER, clazz.getResourceAsStream("goetter/data/masterships-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadAspects(GOETTER, clazz.getResourceAsStream("goetter/data/aspects-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadSpells(GOETTER, clazz.getResourceAsStream("goetter/data/spells-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadEducations(GOETTER, clazz.getResourceAsStream("goetter/data/educations-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadEnhancements(GOETTER, clazz.getResourceAsStream("goetter/data/enhancements-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadMaterials(GOETTER, clazz.getResourceAsStream("goetter/data/materials-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadDeityTypes(GOETTER, clazz.getResourceAsStream("goetter/data/deitytypes-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		SplitterMondCore.loadHolyPowers(GOETTER, clazz.getResourceAsStream("goetter/data/holypowers-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Diener der Götter---------------------------------");
		PluginSkeleton GODSERV = new PluginSkeleton("Goetterdiener", "Diener der Götter");
		SplitterMondCore.loadEducations(GODSERV, clazz.getResourceAsStream("goetterdiener/data/educations-goetterdiener.xml"), GODSERV.getResources(), GODSERV.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Jenseits der Grenzen------------------------------");
		PluginSkeleton JDG = new PluginSkeleton("JDG", "Jenseits der Grenzen");
		SplitterMondCore.loadPowers(JDG, clazz.getResourceAsStream("jdg/data/powers-jdg.xml"), JDG.getResources(), JDG.getHelpResources());
		SplitterMondCore.loadMasterships(JDG, clazz.getResourceAsStream("jdg/data/masterships-jdg.xml"), JDG.getResources(), JDG.getHelpResources());
		SplitterMondCore.loadSpells(JDG, clazz.getResourceAsStream("jdg/data/spells-jdg.xml"), JDG.getResources(), JDG.getHelpResources());
		SplitterMondCore.loadEquipment(JDG, clazz.getResourceAsStream("jdg/data/equipment-jdg.xml"), JDG.getResources(), JDG.getHelpResources());
		SplitterMondCore.loadMaterials(JDG, clazz.getResourceAsStream("jdg/data/materials-jdg.xml"), JDG.getResources(), JDG.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Die Magie-----------------------------------------");
		PluginSkeleton MAGIE = new PluginSkeleton("Magie", "Die Magie");
		SplitterMondCore.loadPowers(MAGIE, clazz.getResourceAsStream("magie/data/powers-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadResources(MAGIE, clazz.getResourceAsStream("magie/data/resources-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadMasterships(MAGIE, clazz.getResourceAsStream("magie/data/masterships-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadSpells(MAGIE, clazz.getResourceAsStream("magie/data/spells-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadEducations(MAGIE, clazz.getResourceAsStream("magie/data/educations-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadEnhancements(MAGIE, clazz.getResourceAsStream("magie/data/enhancements-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadCreatureFeatureTypes(MAGIE, clazz.getResourceAsStream("magie/data/creaturefeaturetypes-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		SplitterMondCore.loadCreatures(MAGIE, clazz.getResourceAsStream("magie/data/creatures-magie.xml"), MAGIE.getResources(), MAGIE.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Sadu----------------------------------------------");
		PluginSkeleton SADU = new PluginSkeleton("SADU", "Sadu");
		SplitterMondCore.loadCultures(SADU, clazz.getResourceAsStream("sadu/data/cultures-sadu.xml"), SADU.getResources(), SADU.getHelpResources());
		SplitterMondCore.loadEquipment(SADU, clazz.getResourceAsStream("sadu/data/equipment-sadu.xml"), SADU.getResources(), SADU.getHelpResources());
		SplitterMondCore.loadEducations(SADU, clazz.getResourceAsStream("sadu/data/educations-sadu.xml"), SADU.getResources(), SADU.getHelpResources());
		SplitterMondCore.loadNameTable(SADU, clazz.getResourceAsStream("sadu/data/nametable-sadu.xml"), SADU.getResources(), SADU.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Surmakar------------------------------------------");
		PluginSkeleton SURM = new PluginSkeleton("Surmakar", "Die Surmakar");
		SplitterMondCore.loadEquipment(SURM, clazz.getResourceAsStream("surmakar/data/equipment-surmakar.xml"), SURM.getResources(), SURM.getHelpResources());
		SplitterMondCore.loadEquipment(SURM, clazz.getResourceAsStream("surmakar/data/alchemy-surmakar.xml"), SURM.getResources(), SURM.getHelpResources());
		SplitterMondCore.loadEducations(SURM, clazz.getResourceAsStream("surmakar/data/educations-surmakar.xml"), SURM.getResources(), SURM.getHelpResources());
		SplitterMondCore.loadMaterials(SURM, clazz.getResourceAsStream("surmakar/data/materials-surmakar.xml"), SURM.getResources(), SURM.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Ungebrochen---------------------------------------");
		PluginSkeleton UNGE = new PluginSkeleton("UNGEBROCHEN", "Ungebrochen");
		SplitterMondCore.loadMasterships(UNGE, clazz.getResourceAsStream("ungebrochen/data/masterships-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEquipment(UNGE, clazz.getResourceAsStream("ungebrochen/data/equipment-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadCreatures(UNGE, clazz.getResourceAsStream("ungebrochen/data/creatures-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEducations(UNGE, clazz.getResourceAsStream("ungebrochen/data/educations-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadNameTable(UNGE, clazz.getResourceAsStream("ungebrochen/data/nametable-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
//		SplitterMondCore.loadMaterials(UNGE, clazz.getResourceAsStream("ungebrochen/data/materials-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEquipment(UNGE, clazz.getResourceAsStream("ungebrochen/data/hiebwaffen-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEquipment(UNGE, clazz.getResourceAsStream("ungebrochen/data/schusswaffen-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEquipment(UNGE, clazz.getResourceAsStream("ungebrochen/data/stangenwaffen-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadEquipment(UNGE, clazz.getResourceAsStream("ungebrochen/data/wurfwaffen-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		SplitterMondCore.loadSpells(UNGE, clazz.getResourceAsStream("ungebrochen/data/spells-ungebrochen.xml"), UNGE.getResources(), UNGE.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Unreich-------------------------------------------");
		PluginSkeleton UNREICH = new PluginSkeleton("Unreich", "Das Unreich");
		SplitterMondCore.loadCultureLores(UNREICH, clazz.getResourceAsStream("unreich/data/culturelores-unreich.xml"), UNREICH.getResources(), UNREICH.getHelpResources());
		SplitterMondCore.loadCultures(UNREICH, clazz.getResourceAsStream("unreich/data/cultures-unreich.xml"), UNREICH.getResources(), UNREICH.getHelpResources());
		SplitterMondCore.loadEquipment(UNREICH, clazz.getResourceAsStream("unreich/data/alchemy-unreich.xml"), UNREICH.getResources(), UNREICH.getHelpResources());
		SplitterMondCore.loadMaterials(UNREICH, clazz.getResourceAsStream("unreich/data/materials-unreich.xml"), UNREICH.getResources(), UNREICH.getHelpResources());
		SplitterMondCore.loadEducations(UNREICH, clazz.getResourceAsStream("unreich/data/educations-unreich.xml"), UNREICH.getResources(), UNREICH.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Zhoujiang-----------------------------------------");
		PluginSkeleton ZHOU = new PluginSkeleton("zhoujiang", "Zhoujiang");
		SplitterMondCore.loadCultureLores(ZHOU, clazz.getResourceAsStream("zhoujiang/data/culturelores-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadFeatureTypes(ZHOU, clazz.getResourceAsStream("zhoujiang/data/featuretypes-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadEquipment(ZHOU, clazz.getResourceAsStream("zhoujiang/data/equipment-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadEquipment(ZHOU, clazz.getResourceAsStream("zhoujiang/data/alchemy-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadMaterials(ZHOU, clazz.getResourceAsStream("zhoujiang/data/materials-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadMasterships(ZHOU, clazz.getResourceAsStream("zhoujiang/data/masterships-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadEducations(ZHOU, clazz.getResourceAsStream("zhoujiang/data/educations-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadSpells(ZHOU, clazz.getResourceAsStream("zhoujiang/data/spells-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		SplitterMondCore.loadNameTable(ZHOU, clazz.getResourceAsStream("zhoujiang/data/nametable-zhoujiang.xml"), ZHOU.getResources(), ZHOU.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Suderinseln---------------------------------------");
		PluginSkeleton SUDER = new PluginSkeleton("Suderinseln", "Die Suderinseln");
		SplitterMondCore.loadEquipment(SUDER, clazz.getResourceAsStream("suderinseln/data/equipment-suderinseln.xml"), SUDER.getResources(), SUDER.getHelpResources());
		SplitterMondCore.loadEducations(SUDER, clazz.getResourceAsStream("suderinseln/data/educations-suderinseln.xml"), SUDER.getResources(), SUDER.getHelpResources());
		SplitterMondCore.loadCreatures(SUDER, clazz.getResourceAsStream("suderinseln/data/creatures-suderinseln.xml"), SUDER.getResources(), SUDER.getHelpResources());
		SplitterMondCore.loadNameTable(SUDER, clazz.getResourceAsStream("suderinseln/data/nametable-schaedel.xml"), SUDER.getResources(), SUDER.getHelpResources());
		SplitterMondCore.loadNameTable(SUDER, clazz.getResourceAsStream("suderinseln/data/nametable-anuu.xml"), SUDER.getResources(), SUDER.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );
		
		logger.info("START -------------------------------Kesh----------------------------------------------");
		PluginSkeleton KESH = new PluginSkeleton("Kesh", "Das Erbe von Kesh");
		SplitterMondCore.loadEquipment(KESH, clazz.getResourceAsStream("kesh/data/equipment-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadEquipment(KESH, clazz.getResourceAsStream("kesh/data/alchemy-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadMaterials(KESH, clazz.getResourceAsStream("kesh/data/materials-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadMasterships(KESH, clazz.getResourceAsStream("kesh/data/masterships-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadSpells(KESH, clazz.getResourceAsStream("kesh/data/spells-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadEducations(KESH, clazz.getResourceAsStream("kesh/data/educations-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadCreatures(KESH, clazz.getResourceAsStream("kesh/data/creatures-kesh.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadNameTable(KESH, clazz.getResourceAsStream("kesh/data/nametable-keshabid.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadNameTable(KESH, clazz.getResourceAsStream("kesh/data/nametable-keshubim.xml"), KESH.getResources(), KESH.getHelpResources());
		SplitterMondCore.loadNameTable(KESH, clazz.getResourceAsStream("kesh/data/nametable-turubar.xml"), KESH.getResources(), KESH.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Mahaluu-------------------------------------------");
		PluginSkeleton MAHA = new PluginSkeleton("Mahaluu", "Mahaluu");
		SplitterMondCore.loadEquipment(MAHA, clazz.getResourceAsStream("mahaluu/data/equipment-mahaluu.xml"), MAHA.getResources(), MAHA.getHelpResources());
		SplitterMondCore.loadEducations(MAHA, clazz.getResourceAsStream("mahaluu/data/educations-mahaluu.xml"), MAHA.getResources(), MAHA.getHelpResources());
		SplitterMondCore.loadCreatures(MAHA, clazz.getResourceAsStream("mahaluu/data/creatures-mahaluu.xml"), MAHA.getResources(), MAHA.getHelpResources());
		SplitterMondCore.loadNameTable(MAHA, clazz.getResourceAsStream("mahaluu/data/nametable-mahaluu.xml"), MAHA.getResources(), MAHA.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START -------------------------------Badashan------------------------------------------");
		PluginSkeleton BAD = new PluginSkeleton("Badashan", "Badashan - Im Reich des Affengottes");
		SplitterMondCore.loadEquipment(BAD, clazz.getResourceAsStream("badashan/data/equipment-badashan.xml"), BAD.getResources(), BAD.getHelpResources());
		SplitterMondCore.loadEquipment(BAD, clazz.getResourceAsStream("badashan/data/alchemy-badashan.xml"), BAD.getResources(), BAD.getHelpResources());
		SplitterMondCore.loadEducations(BAD, clazz.getResourceAsStream("badashan/data/educations-badashan.xml"), BAD.getResources(), BAD.getHelpResources());
		SplitterMondCore.loadNameTable(BAD, clazz.getResourceAsStream("badashan/data/nametable-badashan.xml"), BAD.getResources(), BAD.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );

		logger.info("START ------------------------Mertalische Städtebund-----------------------------------");
		PluginSkeleton MSB = new PluginSkeleton("Staedtebund", "Der Mertalische Städtebund");
		SplitterMondCore.loadEducations(MSB, clazz.getResourceAsStream("staedtebund/data/educations-staedtebund.xml"), MSB.getResources(), MSB.getHelpResources());
		SplitterMondCore.loadNameTable(MSB, clazz.getResourceAsStream("staedtebund/data/nametable-staedtebund.xml"), MSB.getResources(), MSB.getHelpResources());
		count++; callback.progressChanged( (count/totalPlugins) );		

		logger.info("START -------------------------------Arkuri--------------------------------------------");
		PluginSkeleton ARK = new PluginSkeleton("Arkuri", "Arkuri");
		SplitterMondCore.loadEquipment(ARK, clazz.getResourceAsStream("arkuri/data/equipment-arkuri.xml"), ARK.getResources(), ARK.getHelpResources()); 
		SplitterMondCore.loadEducations(ARK, clazz.getResourceAsStream("arkuri/data/educations-arkuri.xml"), ARK.getResources(), ARK.getHelpResources()); 
		SplitterMondCore.loadNameTable(ARK, clazz.getResourceAsStream("arkuri/data/nametable-arkuri.xml"), ARK.getResources(), ARK.getHelpResources()); 
		SplitterMondCore.loadCreatures(ARK, clazz.getResourceAsStream("arkuri/data/creatures-arkuri.xml"), ARK.getResources(), ARK.getHelpResources());
		SplitterMondCore.loadCultures(ARK, clazz.getResourceAsStream("arkuri/data/cultures-arkuri.xml"), ARK.getResources(), ARK.getHelpResources());
		SplitterMondCore.loadMaterials(ARK, clazz.getResourceAsStream("arkuri/data/materials-arkuri.xml"), ARK.getResources(), ARK.getHelpResources());
		SplitterMondCore.loadTowns(ARK, clazz.getResourceAsStream("arkuri/data/towns-arkuri.xml"), ARK.getResources(), ARK.getHelpResources());
		
		logger.info("START -------------------------------Patalis-------------------------------------------");
		PluginSkeleton PUE = new PluginSkeleton("Patalis", "Patalis und Elyrea");
		SplitterMondCore.loadCultures(PUE, clazz.getResourceAsStream("patalis/data/cultures-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		SplitterMondCore.loadMasterships(PUE, clazz.getResourceAsStream("patalis/data/masterships-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		SplitterMondCore.loadResources(PUE, clazz.getResourceAsStream("patalis/data/resources-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		SplitterMondCore.loadCreatures(PUE, clazz.getResourceAsStream("patalis/data/creatures-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		SplitterMondCore.loadEquipment(PUE, clazz.getResourceAsStream("patalis/data/equipment-patalis.xml"), PUE.getResources(), PUE.getHelpResources()); 
		SplitterMondCore.loadEducations(PUE, clazz.getResourceAsStream("patalis/data/educations-patalis.xml"), PUE.getResources(), PUE.getHelpResources()); 
		SplitterMondCore.loadMaterials(PUE, clazz.getResourceAsStream("patalis/data/materials-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		SplitterMondCore.loadNameTable(PUE, clazz.getResourceAsStream("patalis/data/nametable-patalis.xml"), PUE.getResources(), PUE.getHelpResources()); 
		SplitterMondCore.loadNameTable(PUE, clazz.getResourceAsStream("patalis/data/nametable-elyrvolk.xml"), PUE.getResources(), PUE.getHelpResources()); 		
		SplitterMondCore.loadTowns(PUE, clazz.getResourceAsStream("patalis/data/towns-patalis.xml"), PUE.getResources(), PUE.getHelpResources());
		// Following line moved here, because education "vordanshield" moved to PoE and is referenced in this file
		SplitterMondCore.loadDeities(GOETTER, clazz.getResourceAsStream("goetter/data/deities-goetter.xml"), GOETTER.getResources(), GOETTER.getHelpResources());
		
		logger.info("START ------------------------------Rattlinge--------------------------------------------");
		PluginSkeleton RAT = new PluginSkeleton("Rattlinge", "Rattlinge");
		SplitterMondCore.loadPowers(RAT, clazz.getResourceAsStream("rattlinge/data/powers-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());
		SplitterMondCore.loadCultureLores(RAT, clazz.getResourceAsStream("rattlinge/data/culturelores-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());
		SplitterMondCore.loadLanguages(RAT, clazz.getResourceAsStream("rattlinge/data/languages-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());
		SplitterMondCore.loadRaces(RAT, clazz.getResourceAsStream("rattlinge/data/races-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());
		SplitterMondCore.loadCultures(RAT, clazz.getResourceAsStream("rattlinge/data/cultures-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());
		SplitterMondCore.loadMasterships(RAT, clazz.getResourceAsStream("rattlinge/data/masterships-rattlinge.xml"), RAT.getResources(), RAT.getHelpResources());

		logger.info("START ------------------------------Frynjord--------------------------------------------");
		PluginSkeleton FRY = new PluginSkeleton("Frynjord", "Frynjord");
		SplitterMondCore.loadEducations(FRY, clazz.getResourceAsStream("frynjord/data/educations-frynjord.xml"), FRY.getResources(), FRY.getHelpResources());
		SplitterMondCore.loadEquipment(FRY, clazz.getResourceAsStream("frynjord/data/equipment-frynjord.xml"), FRY.getResources(), FRY.getHelpResources());
		SplitterMondCore.loadNameTable(FRY, clazz.getResourceAsStream("frynjord/data/nametable-frynjord.xml"), FRY.getResources(), FRY.getHelpResources()); 
		SplitterMondCore.loadTowns(FRY, clazz.getResourceAsStream("frynjord/data/towns-frynjord.xml"), FRY.getResources(), FRY.getHelpResources());
		
		count++; callback.progressChanged( (count/totalPlugins) );

		// Load after Magie and Zhoujiang: the educations reuse their masterships.
		logger.info("START ------------------------------Schleier--------------------------------------------");
		PluginSkeleton SCHLEIER = new PluginSkeleton("Schleier", "Hinter dem Schleier");
		SplitterMondCore.loadMasterships(SCHLEIER, clazz.getResourceAsStream("schleier/data/masterships-schleier.xml"), SCHLEIER.getResources(), SCHLEIER.getHelpResources());
		SplitterMondCore.loadEducations(SCHLEIER, clazz.getResourceAsStream("schleier/data/educations-schleier.xml"), SCHLEIER.getResources(), SCHLEIER.getHelpResources());
		SplitterMondCore.loadMaterials(SCHLEIER, clazz.getResourceAsStream("schleier/data/materials-schleier.xml"), SCHLEIER.getResources(), SCHLEIER.getHelpResources());
		SplitterMondCore.loadEquipment(SCHLEIER, clazz.getResourceAsStream("schleier/data/equipment-schleier.xml"), SCHLEIER.getResources(), SCHLEIER.getHelpResources());

		/*
		 * Load custom data
		 */
		logger.info("START -------------------------------Custom------------------------------------------");
		if (CustomDataHandlerLoader.getInstance()!=null) {
			CustomDataHandler custom = CustomDataHandlerLoader.getInstance();
			List<String> customIDs = custom.getAvailableCustomIDs(RoleplayingSystem.SPLITTERMOND);
			Collections.sort(customIDs, new Comparator<String>() {
				public int compare(String c1, String c2) {
					if (c1.startsWith("skill"))
						return -1;
					if (c1.startsWith("education") || c1.startsWith("race") || c1.startsWith("background"))
						return  1;
					return 0;
				}});
			PluginSkeleton CUSTOM = new PluginSkeleton("Custom", "Custom Data");
			for (String id : customIDs) {
				CustomDataPackage  bundle = custom.getCustomData(RoleplayingSystem.SPLITTERMOND, id);
				if (bundle!=null) {
					try (InputStream datastream = new FileInputStream(bundle.datafile.toFile())) {
						if (id.startsWith("item") || id.startsWith("equipment") || id.startsWith("gear")) {
							SplitterMondCore.loadEquipment(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("skills")) {
							SplitterMondCore.loadSkills(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("masterships")) {
							SplitterMondCore.loadMasterships(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("education")) {
							SplitterMondCore.loadEducations(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("spell")) {
							SplitterMondCore.loadSpells(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("enhancement")) {
							SplitterMondCore.loadEnhancements(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("power")) {
							SplitterMondCore.loadPowers(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("background")) {
							SplitterMondCore.loadBackgrounds(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("material")) {
							SplitterMondCore.loadMaterials(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("race")) {
							SplitterMondCore.loadRaces(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("creature")) {
							SplitterMondCore.loadCreatures(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("cultlore")) {
							SplitterMondCore.loadCultureLores(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else if (id.startsWith("language")) {
							SplitterMondCore.loadLanguages(CUSTOM, datastream, bundle.properties, bundle.helpProperties);
						} else  {
							logger.warn("Don't know how to deal with custom data "+bundle.datafile);
							continue;
						}
						logger.info("Loaded custom data  "+bundle.datafile);
					} catch (Exception e) {
						logger.error("Failed for custom data "+bundle.datafile+" and its properties",e);
					}
				}
			}
		}
		callback.progressChanged( 1.0f );
		BasePluginData.flushMissingKeys();
		logger.debug("STOP  Initialize");
//		logger.fatal("Stop here");
//		System.exit(0);
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.core.CommandBusListener#willProcessCommand(java.lang.Object, de.rpgframework.core.CommandType, java.lang.Object[])
	 */
	@Override
	public boolean willProcessCommand(Object src, CommandType type,
			Object... values) {
		return false;
	}

	//--------------------------------------------------------------------
	/**
	 * @see de.rpgframework.core.CommandBusListener#handleCommand(java.lang.Object, de.rpgframework.core.CommandType, java.lang.Object[])
	 */
	@Override
	public CommandResult handleCommand(Object src, CommandType type,
			Object... values) {
		return null;
	}

	//-------------------------------------------------------------------
	/**
	 * @see de.rpgframework.RulePlugin#getAboutHTML()
	 */
	@Override
	public InputStream getAboutHTML() {
		return ClassLoader.getSystemResourceAsStream("i18n/splittermond/buu.html");
	}

	//-------------------------------------------------------------------
	@Override
	public List<String> getLanguages() {
		return Arrays.asList(Locale.GERMAN.getLanguage());
	}

}
