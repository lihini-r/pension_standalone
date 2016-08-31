package org.pensions.data.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.pensions.data.SqliteDbConnManager;
import org.pensions.data.dao.BankDAO;
import org.pensions.model.BankDTO;
import org.pensions.model.BranchDTO;
import org.pensions.model.lists.BankList;

public class SqliteDAO {
	
	SqliteDbConnManager dbConnManager = null;
	
	public SqliteDAO() {
		dbConnManager = new SqliteDbConnManager();
	}
	
	public void craeteTable() {
		Connection dbConn = null;

		String sql = "CREATE TABLE IF NOT EXISTS `BANK` (`ID` INT NOT NULL,`NAME` TEXT NOT NULL,PRIMARY KEY(ID));CREATE TABLE IF NOT EXISTS `Office` (`institution_name` INTEGER );CREATE TABLE IF NOT EXISTS `branch` (`id` INTEGER PRIMARY KEY AUTOINCREMENT,`branchName` TEXT,`bankId` INTEGER);CREATE TABLE IF NOT EXISTS `designations` (`index` INTEGER PRIMARY KEY AUTOINCREMENT,`designation` TEXT NOT NULL)";
		String insertSql = "INSERT INTO `Office` (`institution_name`) VALUES"+
				"('Central Provincial Council'),"+
				"('Co-operative Employees Commission'),"+
				"('Courts Administration'),"+
				"('District Secretariat - Ampara'),"+
				"('District Secretariat - Anuradhapura'),"+
				"('District Secretariat - Badulla'),"+
				"('District Secretariat - Batticaloa'),"+
				"('District Secretariat - Colombo'),"+
				"('District Secretariat - Galle'),"+
				"('District Secretariat - Gampaha'),"+
				"('District Secretariat - Hambantota'),"+
				"('District Secretariat - Jaffna'),"+
				"('District Secretariat - Kalutara'),"+
				"('District Secretariat - Kandy'),"+
				"('District Secretariat - Kegalle'),"+
				"('District Secretariat - Killinochchi'),"+
				"('District Secretariat - Kurunegala'),"+
				"('District Secretariat - Mannar '),"+
				"('District Secretariat - Matale'),"+
				"('District Secretariat - Monaragala '),"+
				"('District Secretariat - Mullativu'),"+
				"('District Secretariat - Nuwara Eliya'),"+
				"('District Secretariat - Polonnaruwa'),"+
				"('District Secretariat - Puttalam '),"+
				"('District Secretariat - Ratnapura'),"+
				"('District Secretariat - Trincomalee'),"+
				"('District Secretariat - Vauniya'),"+
				"('District Secretariat- Matara'),"+
				"('Eastern Provincial Council'),"+
				"('Government Factory'),"+
				"('National Intellectual Property Office of Sri Lanka '),"+
				"('North Central Provincial Council'),"+
				"('North Western Provincial Council'),"+
				"('Northern Provincial Council'),"+
				"('Registar of Supreme Court'),"+
				"('Sabaragamuwa Provincial Council'),"+
				"('Southern Provincial Council'),"+
				"('Sri Lanka Air Force '),"+
				"('Sri Lanka Army'),"+
				"('Sri Lanka Customs '),"+
				"('Sri Lanka Navy'),"+
				"('University Grants Commission'),"+
				"('Uva Provincial Council'),"+
				"('Western Provincial Council')";
		
		String insertDesignation = "INSERT INTO `designations` (`index`, `designation`) VALUES "+
				"(1, 'A/C Mechanic'),"+
				"(2, 'Accountant'),"+
				"(3, 'Accounts Analyst'),"+
				"(4, 'Accounts Asst.'),"+
				"(5, 'Accounts Officer'),"+
				"(6, 'Addl. Chief Valuer'),"+
				"(7, 'Addl. Commissioner'),"+
				"(8, 'Addl. Commissioner '),"+
				"(9, 'Addl. Commissioner General'),"+
				"(10, 'Addl. Commissioner General of Labour'),"+
				"(11, 'Addl. Conservator General'),"+
				"(12, 'Addl. Director'),"+
				"(13, 'Addl. Director General'),"+
				"(14, 'Addl. Director General of Information'),"+
				"(15, 'Addl. District Registrar'),"+
				"(16, 'Addl. District Secretary'),"+
				"(17, 'Addl. Divisional Secretary'),"+
				"(18, 'Addl. Explosive Controller'),"+
				"(19, 'Addl. General Manager'),"+
				"(20, 'Addl. Government Analyst'),"+
				"(21, 'Addl. Govt.Printer'),"+
				"(22, 'Addl. Import & Export Controller'),"+
				"(23, 'Addl. Legal Draughtsman'),"+
				"(24, 'Addl. Public Trustee'),"+
				"(25, 'Addl. Registrar General'),"+
				"(26, 'Addl. Secretary'),"+
				"(27, 'Addl. Secretary to the President'),"+
				"(28, 'Addl. Solicitor General'),"+
				"(29, 'Addl. Survey General'),"+
				"(30, 'Admin Asst.'),"+
				"(31, 'Admin Grama Niladari'),"+
				"(32, 'Administrative and Accounts Asst.'),"+
				"(33, 'Administrative Asst.'),"+
				"(34, 'Administrative Cordinator'),"+
				"(35, 'Administrative Officer'),"+
				"(36, 'Advertising Asst.'),"+
				"(37, 'Advisor'),"+
				"(38, 'Agrarian Bank Project Asst.'),"+
				"(39, 'Agrarian Devp. Officer'),"+
				"(40, 'Agrarian Research & Production Asst.'),"+
				"(41, 'Agriculture Conductor'),"+
				"(42, 'Agriculture Extension Officer'),"+
				"(43, 'Agriculture Instructor'),"+
				"(44, 'Agriculture Monitoring Officer'),"+
				"(45, 'Agriculture Overseer'),"+
				"(46, 'Air Condition Machine Operator'),"+
				"(47, 'Air Condition Operator'),"+
				"(48, 'Air Condition Technician/Cool Room'),"+
				"(49, 'Air Conditioning & Refregeration Technician'),"+
				"(50, 'Air Photographer & Lab Technician'),"+
				"(51, 'Aircraft Loader'),"+
				"(52, 'Ambassador'),"+
				"(53, 'Ammonia Plan Painting Oper.'),"+
				"(54, 'Animal Breeding Specialist'),"+
				"(55, 'Animal Keeper'),"+
				"(56, 'Animal Maintenance Officer'),"+
				"(57, 'Animal Quarantine Officer'),"+
				"(58, 'Animal Supervisor'),"+
				"(59, 'Animal Unit Controller'),"+
				"(60, 'Announcer'),"+
				"(61, 'Anthropology Asst.'),"+
				"(62, 'Anti Malaria Sprayer'),"+
				"(63, 'Apprentice Translator'),"+
				"(64, 'Aquarium Attendent'),"+
				"(65, 'Arachchi'),"+
				"(66, 'Archaeological Research Asst.'),"+
				"(67, 'Architect'),"+
				"(68, 'Archival Asst.'),"+
				"(69, 'Archival Officer'),"+
				"(70, 'Archival Research Asst.'),"+
				"(71, 'Armament'),"+
				"(72, 'Armed Guard'),"+
				"(73, 'Armeture Winder'),"+
				"(74, 'Art Demonstrator'),"+
				"(75, 'Art Editor'),"+
				"(76, 'Artist'),"+
				"(77, 'Artist & model'),"+
				"(78, 'Asst Chief Health Education Officer'),"+
				"(79, 'Asst Director'),"+
				"(80, 'Asst. / Dy. Director'),"+
				"(81, 'Asst. / Dy. Superintendent'),"+
				"(82, 'Asst. Archivist'),"+
				"(83, 'Asst. Auditor General'),"+
				"(84, 'Asst. Author'),"+
				"(85, 'Asst. Bungalow Keeper'),"+
				"(86, 'Asst. Cameraman'),"+
				"(87, 'Asst. Civil Admin Officer'),"+
				"(88, 'Asst. Civil Engineer'),"+
				"(89, 'Asst. Commissioner'),"+
				"(90, 'Asst. Commissioner of Labour'),"+
				"(91, 'Asst. Computer Programmer'),"+
				"(92, 'Asst. Conservator'),"+
				"(93, 'Asst. Cook'),"+
				"(94, 'Asst. Cultural Promotion Officer'),"+
				"(95, 'Asst. Curator'),"+
				"(96, 'Asst. Data Controller'),"+
				"(97, 'Asst. Director'),"+
				"(98, 'Asst. Director /Dy. Director'),"+
				"(99, 'Asst. Director General'),"+
				"(100, 'Asst. Director/Dy.Director'),"+
				"(101, 'Asst. District Registar'),"+
				"(102, 'Asst. District Secretary'),"+
				"(103, 'Asst. District Valuer'),"+
				"(104, 'Asst. Divisional Secretary'),"+
				"(105, 'Asst. Driector'),"+
				"(106, 'Asst. Drilling Superintendent'),"+
				"(107, 'Asst. Editor of Hansard'),"+
				"(108, 'Asst. Excise Commissioner'),"+
				"(109, 'Asst. Explosive Controller'),"+
				"(110, 'Asst. Film Librarian'),"+
				"(111, 'Asst. Forman'),"+
				"(112, 'Asst. Government Analyst'),"+
				"(113, 'Asst. Government Engineering and Ships Surveyor'),"+
				"(114, 'Asst. Hindu Piligrims Rest Guardian'),"+
				"(115, 'Asst. House-keeper'),"+
				"(116, 'Asst. Inspector of Watchers'),"+
				"(117, 'Asst. Investigating Officer'),"+
				"(118, 'Asst. Land Use Planning Officer'),"+
				"(119, 'Asst. Lecturer'),"+
				"(120, 'Asst. Legal Advisor'),"+
				"(121, 'Asst. Legal Draughtsman'),"+
				"(122, 'Asst. Legal Officer'),"+
				"(123, 'Asst. Librarian'),"+
				"(124, 'Asst. Material Superintendent'),"+
				"(125, 'Asst. Officer in Charge'),"+
				"(126, 'Asst. Parliament Officer'),"+
				"(127, 'Asst. Parliamentary Protocol Officer'),"+
				"(128, 'Asst. Photographer'),"+
				"(129, 'Asst. Post Superintendent'),"+
				"(130, 'Asst. Principal Officer'),"+
				"(131, 'Asst. Programme Manager'),"+
				"(132, 'Asst. Programmer'),"+
				"(133, 'Asst. Registrar'),"+
				"(134, 'Asst. Research Officer'),"+
				"(135, 'Asst. Secretary'),"+
				"(136, 'Asst. Secretary General of Parliament'),"+
				"(137, 'Asst. Secretary to the President'),"+
				"(138, 'Asst. Security Officer'),"+
				"(139, 'Asst. Sergeant-At-Arms'),"+
				"(140, 'Asst. Shipping Officer'),"+
				"(141, 'Asst. Soil Chemist'),"+
				"(142, 'Asst. State Attorney'),"+
				"(143, 'Asst. Store Keeper'),"+
				"(144, 'Asst. Superintendent'),"+
				"(145, 'Asst. Superintendent of Customs'),"+
				"(146, 'Asst. Superintendent of Police'),"+
				"(147, 'Asst. Superintendent of Prison'),"+
				"(148, 'Asst. Survey Superintendent'),"+
				"(149, 'Asst. Title Investigation Officer'),"+
				"(150, 'Asst. Transpotation Superintendent'),"+
				"(151, 'Asst. Video Camaraman'),"+
				"(152, 'Asst. Warden'),"+
				"(153, 'Asst. Warehouseman'),"+
				"(154, 'Asst. Work Manager (Project)'),"+
				"(155, 'Asst.Community Health Medical Officer'),"+
				"(156, 'Asst.Dy.Commissioner'),"+
				"(157, 'Asst/ Dy. Food Commissioner'),"+
				"(158, 'Asst/ Dy. Govt. Printer'),"+
				"(159, 'Attache/Protocol Asst.'),"+
				"(160, 'Attendant'),"+
				"(161, 'Attorney General'),"+
				"(162, 'Audio Controller Aide'),"+
				"(163, 'Audio Video Asst.'),"+
				"(164, 'Audio Video Recorder'),"+
				"(165, 'Audio Visual Machine Operator'),"+
				"(166, 'Audio Visual Technician'),"+
				"(167, 'Audiology Technician'),"+
				"(168, 'Audio-visual Asst.'),"+
				"(169, 'Audio-visual Officer'),"+
				"(170, 'Audio-visual Technical'),"+
				"(171, 'Audit Asst.'),"+
				"(172, 'Audit Clerk'),"+
				"(173, 'Audit Examiner'),"+
				"(174, 'Auditor General'),"+
				"(175, 'Author'),"+
				"(176, 'Authorised Officer'),"+
				"(177, 'Auto Electrician'),"+
				"(178, 'Ayurveda Community Health Promotional Medical Officer'),"+
				"(179, 'Ayurvedic Medical Officer'),"+
				"(180, 'Baco Operator'),"+
				"(181, 'Baker'),"+
				"(182, 'Bar Steward'),"+
				"(183, 'Barber'),"+
				"(184, 'Barman'),"+
				"(185, 'Basic Technician'),"+
				"(186, 'Battery Mechanic'),"+
				"(187, 'Beat Forest Officer'),"+
				"(188, 'Bee Demonstrator'),"+
				"(189, 'Bee Keeper'),"+
				"(190, 'Bench Fitter'),"+
				"(191, 'Bicycle Messenger'),"+
				"(192, 'Bicycle Repairer'),"+
				"(193, 'Bio Chemist'),"+
				"(194, 'Bio Medical Engineer'),"+
				"(195, 'Bio Medical Engineering Assistant'),"+
				"(196, 'Biologist'),"+
				"(197, 'Blacksmith'),"+
				"(198, 'Board Secretary'),"+
				"(199, 'Boat Builder'),"+
				"(200, 'Boat Operator'),"+
				"(201, 'Boiler'),"+
				"(202, 'Boiler Operator'),"+
				"(203, 'Book Binder'),"+
				"(204, 'Book Binder & Machine Operator'),"+
				"(205, 'Book Binder & Map Mounter'),"+
				"(206, 'Book Keeper'),"+
				"(207, 'Botanical Science Asst.'),"+
				"(208, 'Budder'),"+
				"(209, 'Budget Assistant'),"+
				"(210, 'Building Caretaker'),"+
				"(211, 'Building Examiner'),"+
				"(212, 'Building Maintenance Officer'),"+
				"(213, 'Building Overseer'),"+
				"(214, 'Building Supervisor'),"+
				"(215, 'Bungalow Keeper'),"+
				"(216, 'Bungalow Keeper Aide'),"+
				"(217, 'Bungalow Manager (Nuwara Eliya)'),"+
				"(218, 'Bunglow Keeper'),"+
				"(219, 'Bus Conductor'),"+
				"(220, 'Business Analyst Assistant'),"+
				"(221, 'Butler'),"+
				"(222, 'Cable Jointer'),"+
				"(223, 'Camara Operator'),"+
				"(224, 'Camera Aide'),"+
				"(225, 'Camera Asst.'),"+
				"(226, 'Camera Operator'),"+
				"(227, 'Camera Operator Aide'),"+
				"(228, 'Cameraman'),"+
				"(229, 'Cameraman Aide'),"+
				"(230, 'Care Taker'),"+
				"(231, 'Career Guidance Officer'),"+
				"(232, 'Carpenter'),"+
				"(233, 'Carrier Guide Officer'),"+
				"(234, 'Cashier/ Clerk'),"+
				"(235, 'Cataloging Asst.'),"+
				"(236, 'CB.Keeper'),"+
				"(237, 'Centre Attendant'),"+
				"(238, 'Chain Saw Operator'),"+
				"(239, 'Charge Collector Asst.'),"+
				"(240, 'Charity Asst.'),"+
				"(241, 'Charwoman'),"+
				"(242, 'Chauffeur'),"+
				"(243, 'Checker'),"+
				"(244, 'Chef-de-Partie'),"+
				"(245, 'Chemical conservation Officer'),"+
				"(246, 'Chemist'),"+
				"(247, 'Chemist and Methodologist'),"+
				"(248, 'Chief Accountant'),"+
				"(249, 'Chief Agronomist'),"+
				"(250, 'Chief Animal Keeper'),"+
				"(251, 'Chief Animal Quarantine Officer'),"+
				"(252, 'Chief Author'),"+
				"(253, 'Chief Building Examiner'),"+
				"(254, 'Chief Documents Repairer'),"+
				"(255, 'Chief Draughtsman'),"+
				"(256, 'Chief Drawing Office Asst.'),"+
				"(257, 'Chief Editor'),"+
				"(258, 'Chief Engineer'),"+
				"(259, 'Chief Epidemiologist'),"+
				"(260, 'Chief Excise Inspector'),"+
				"(261, 'Chief Finance Officer'),"+
				"(262, 'Chief Financial Officer'),"+
				"(263, 'Chief Foreman/ Chief Printing Editor'),"+
				"(264, 'Chief Guard'),"+
				"(265, 'Chief Inspector'),"+
				"(266, 'Chief Inspector of Customs'),"+
				"(267, 'Chief Inspector of Police'),"+
				"(268, 'Chief Instructor'),"+
				"(269, 'Chief Internal Auditor'),"+
				"(270, 'Chief Jailor'),"+
				"(271, 'Chief K.K.S.'),"+
				"(272, 'Chief Legal Officer'),"+
				"(273, 'Chief Livestock Economist'),"+
				"(274, 'Chief Mahout'),"+
				"(275, 'Chief of National Intelligence'),"+
				"(276, 'Chief Parliamentary Interpreter'),"+
				"(277, 'Chief Producer'),"+
				"(278, 'Chief Redio Officer'),"+
				"(279, 'Chief Registrar'),"+
				"(280, 'Chief Research Officer'),"+
				"(281, 'Chief Security Officer'),"+
				"(282, 'Chief Supervision Manager'),"+
				"(283, 'Chief Technical & Administrative Officer'),"+
				"(284, 'Chief Train Controller'),"+
				"(285, 'Chief Translator'),"+
				"(286, 'Chief Vaccinologist'),"+
				"(287, 'Chief Valuer'),"+
				"(288, 'Chief Welfare Officer'),"+
				"(289, 'Chief Work Inspector'),"+
				"(290, 'Chife Accountant'),"+
				"(291, 'Chife Coast Conservation Superviser'),"+
				"(292, 'Chife Engineer'),"+
				"(293, 'Chife Internal Auditor'),"+
				"(294, 'Chife Work Supervisor'),"+
				"(295, 'Child Devp. Aide'),"+
				"(296, 'Child Right Promotion Asst.'),"+
				"(297, 'Cinema Operator'),"+
				"(298, 'Cinema Technician'),"+
				"(299, 'Circuit Bangalow Keeper'),"+
				"(300, 'Circuit Bungalow Asst. Care-taker'),"+
				"(301, 'Circuit Bungalow Keeper'),"+
				"(302, 'Circuit Bungalow Labourer'),"+
				"(303, 'Civil Administrative Director'),"+
				"(304, 'Civil Administrative Officer'),"+
				"(305, 'Civil Engineer'),"+
				"(306, 'Civil Engineer Asst.'),"+
				"(307, 'Civil Engineering Materials Survey Superintendent'),"+
				"(308, 'Civil Engineering Materials Surveyor'),"+
				"(309, 'Civil Security'),"+
				"(310, 'Cleaner'),"+
				"(311, 'Clerical Officer (Defence My)'),"+
				"(312, 'Clerk'),"+
				"(313, 'Clerk / Typist'),"+
				"(314, 'Clerk Asst.'),"+
				"(315, 'Clerk cum Typist'),"+
				"(316, 'Clerk/ Typist'),"+
				"(317, 'Coach'),"+
				"(318, 'Coach Trimmer'),"+
				"(319, 'Coast Conservation Guard'),"+
				"(320, 'Coast Conservation Sarayan'),"+
				"(321, 'Coast Conservation Supervisor'),"+
				"(322, 'Code Clerk'),"+
				"(323, 'Collector of Medicinal Plants'),"+
				"(324, 'Colonization Officer'),"+
				"(325, 'Comito Meter Operator'),"+
				"(326, 'Commerce Asst.'),"+
				"(327, 'Commerce Research Asst.'),"+
				"(328, 'Commissioner'),"+
				"(329, 'Commissioner for Workmens Compenstion'),"+
				"(330, 'Commissioner General'),"+
				"(331, 'Commissioner General of Labour'),"+
				"(332, 'Committee Clerk'),"+
				"(333, 'Committee Reporter'),"+
				"(334, 'Communication & IT Asst.'),"+
				"(335, 'Communication Asst.'),"+
				"(336, 'Communication System Asst.'),"+
				"(337, 'Communication Technician'),"+
				"(338, 'Communication/Security Engineer'),"+
				"(339, 'Community Correction Officer'),"+
				"(340, 'Community Health Development Officer'),"+
				"(341, 'Community Health Social Work Officer'),"+
				"(342, 'Community Outreach & Extention Asst.'),"+
				"(343, 'Compositor'),"+
				"(344, 'Compto Meter Operator'),"+
				"(345, 'Computer Data Operator'),"+
				"(346, 'Computer Designer'),"+
				"(347, 'Computer Maintainer'),"+
				"(348, 'Computer Network Administrator'),"+
				"(349, 'Computer Operator'),"+
				"(350, 'Computer Programmer'),"+
				"(351, 'Computer System Analyst'),"+
				"(352, 'Computer Technical Officer'),"+
				"(353, 'Computer Technician'),"+
				"(354, 'Computer Type Setter'),"+
				"(355, 'Computer Typist'),"+
				"(356, 'Confidential Secretary'),"+
				"(357, 'Conservation Asst.'),"+
				"(358, 'Conservation Attendant'),"+
				"(359, 'Conservation Officer'),"+
				"(360, 'Conservator General'),"+
				"(361, 'Conservator of Forest'),"+
				"(362, 'Consul General'),"+
				"(363, 'Consular Asst.'),"+
				"(364, 'Consultant'),"+
				"(365, 'Control Room Operator'),"+
				"(366, 'Cook'),"+
				"(367, 'Cook & Asst. Cook'),"+
				"(368, 'Cook Helper'),"+
				"(369, 'Cooks'),"+
				"(370, 'Co-operative Devp. Officer'),"+
				"(371, 'Co-operative Promotion & Investigation Asst.'),"+
				"(372, 'Co-ordinating Engineer'),"+
				"(373, 'Coordinating for Secretary'),"+
				"(374, 'Coordinator Disaster Management Operations'),"+
				"(375, 'Coordinator of Buddhist Affairs'),"+
				"(376, 'Coordinator Pharmacists School'),"+
				"(377, 'Cord. Asst.'),"+
				"(378, 'Cord. Officer'),"+
				"(379, 'Cord. Secretary'),"+
				"(380, 'Cord. Secretary for Secretary'),"+
				"(381, 'Cord. Secy. for Commissioner'),"+
				"(382, 'Corrector'),"+
				"(383, 'Corsetiere'),"+
				"(384, 'Counseling Asst.'),"+
				"(385, 'Counseling Center Labourer'),"+
				"(386, 'counseling Officer'),"+
				"(387, 'Counter & Packer'),"+
				"(388, 'Counterman/Storekeeper'),"+
				"(389, 'Court Security'),"+
				"(390, 'Craftsman'),"+
				"(391, 'Crane Operator'),"+
				"(392, 'Crier & Enterer'),"+
				"(393, 'Criminologist'),"+
				"(394, 'Cultivation Labourer'),"+
				"(395, 'Cultivation Watcher'),"+
				"(396, 'Cultural Asst.'),"+
				"(397, 'Cultural Asst. Director'),"+
				"(398, 'Cultural Devp. Asst./Cultural Officer'),"+
				"(399, 'Cultural Officer'),"+
				"(400, 'Cultural Promotion Officer'),"+
				"(401, 'Cultural Sevaka'),"+
				"(402, 'Curator'),"+
				"(403, 'Customs Guard'),"+
				"(404, 'Cycle Messenger'),"+
				"(405, 'Cycle Orderly'),"+
				"(406, 'Dairy Engineering Specialist'),"+
				"(407, 'Dance Performer'),"+
				"(408, 'Dancer'),"+
				"(409, 'Dark Room Assistant'),"+
				"(410, 'Dark Room Attendent'),"+
				"(411, 'Dark Room Labourer'),"+
				"(412, 'Dark Room Photograph Printer'),"+
				"(413, 'Data Asst.'),"+
				"(414, 'Data Bank Operator'),"+
				"(415, 'Data Controller'),"+
				"(416, 'Data Entry Operator'),"+
				"(417, 'Data Entry Operator/Code Clerk'),"+
				"(418, 'Data Entry Operator/ICT Assistant'),"+
				"(419, 'Data Management Asst.'),"+
				"(420, 'Data Operator'),"+
				"(421, 'Database Administrator'),"+
				"(422, 'Demonstrator'),"+
				"(423, 'Dental Surgeon'),"+
				"(424, 'Dental Technician'),"+
				"(425, 'Department Clerk'),"+
				"(426, 'Department Clerk/Typist'),"+
				"(427, 'Deputy Director'),"+
				"(428, 'Deputy Director (Nutrition Cordination)'),"+
				"(429, 'Deputy Director / Chief Health Education Officer'),"+
				"(430, 'Deputy Director General'),"+
				"(431, 'Deputy Director General (Bio Medical Engineering)'),"+
				"(432, 'Deputy Director General (NHSL)'),"+
				"(433, 'Designer'),"+
				"(434, 'Dev.Coordinator'),"+
				"(435, 'Development Assistant'),"+
				"(436, 'Devp. Coordinator'),"+
				"(437, 'Devp. Officer / Asst.'),"+
				"(438, 'Diet Stewards'),"+
				"(439, 'Diplomatic Officer'),"+
				"(440, 'Director'),"+
				"(441, 'Director '),"+
				"(442, 'Director - RMO'),"+
				"(443, 'Director (Public Health Veterinary Services)'),"+
				"(444, 'Director General'),"+
				"(445, 'Dispatch Asst.'),"+
				"(446, 'Dispenser'),"+
				"(447, 'Dispensery Clerk'),"+
				"(448, 'Distric Land Use Planning Officer'),"+
				"(449, 'District Inspector'),"+
				"(450, 'District Mesurements Investigation Asst.'),"+
				"(451, 'District Secretary'),"+
				"(452, 'District Sports Officer'),"+
				"(453, 'District Valuer'),"+
				"(454, 'Divisional Asst.'),"+
				"(455, 'Divisional Pharmacist'),"+
				"(456, 'Divisional Secretary'),"+
				"(457, 'Document & Data Asst.'),"+
				"(458, 'Document Asst.'),"+
				"(459, 'Document Investigation & Technology Asst.'),"+
				"(460, 'Document Repairer'),"+
				"(461, 'Documentary Asst.'),"+
				"(462, 'Documentation Conservator'),"+
				"(463, 'Documentation Officer'),"+
				"(464, 'Documentation Supervisor'),"+
				"(465, 'Documents Repairer'),"+
				"(466, 'Domestic Labourer'),"+
				"(467, 'Donor Recruitment Officer'),"+
				"(468, 'Doser Machine Aide'),"+
				"(469, 'Doser Operator'),"+
				"(470, 'Draughtsman'),"+
				"(471, 'Drawing Office Asst.'),"+
				"(472, 'Drill Instructor'),"+
				"(473, 'Driller/ Blaster'),"+
				"(474, 'Drilling Asst.'),"+
				"(475, 'Drilling Superintendent'),"+
				"(476, 'Driver'),"+
				"(477, 'Driver Asst.'),"+
				"(478, 'Driver Cum Messenger'),"+
				"(479, 'Driver Engine'),"+
				"(480, 'Drummer'),"+
				"(481, 'Duplicating Machine Operator'),"+
				"(482, 'Duplicating/ Ronio Machine Operator'),"+
				"(483, 'Duplo Mechine Operator'),"+
				"(484, 'Dy Conservator'),"+
				"(485, 'Dy Director'),"+
				"(486, 'Dy. Auditor General'),"+
				"(487, 'Dy. Author/ Asst. Author'),"+
				"(488, 'Dy. Ayurveda Commissioner'),"+
				"(489, 'Dy. Chief Engineer'),"+
				"(490, 'Dy. Chief of Mission'),"+
				"(491, 'Dy. Chief Parliamentary Interpreter'),"+
				"(492, 'Dy. Chief Valuar'),"+
				"(493, 'Dy. Commissioner'),"+
				"(494, 'Dy. Commissioner / Asst. Commissioner'),"+
				"(495, 'Dy. Commissioner for Workmens Compenstion'),"+
				"(496, 'Dy. Commissioner General'),"+
				"(497, 'Dy. Commissioner of Labour'),"+
				"(498, 'Dy. Controller/Asst.Controller'),"+
				"(499, 'Dy. Co-ordinating Engineer'),"+
				"(500, 'Dy. Director'),"+
				"(501, 'Dy. Director /Asst. Director'),"+
				"(502, 'Dy. Director General'),"+
				"(503, 'Dy. Director of Measurement Unit'),"+
				"(504, 'Dy. Editor of Hansard'),"+
				"(505, 'Dy. Excise Commissioner'),"+
				"(506, 'Dy. Factory Engineer'),"+
				"(507, 'Dy. General Manager'),"+
				"(508, 'Dy. Government Analyst'),"+
				"(509, 'Dy. High Commisioner'),"+
				"(510, 'Dy. Legal Advisor'),"+
				"(511, 'Dy. Postmaster General'),"+
				"(512, 'Dy. Principal'),"+
				"(513, 'Dy. Public Trustee'),"+
				"(514, 'Dy. Registrar'),"+
				"(515, 'Dy. Registrar of Companies'),"+
				"(516, 'Dy. Secretary'),"+
				"(517, 'Dy. Secretary General of Parliament'),"+
				"(518, 'Dy. Secretary to the Treasury'),"+
				"(519, 'Dy. Sergeant-At-Arms'),"+
				"(520, 'Dy. Solicitor General'),"+
				"(521, 'Dy. Superintendent'),"+
				"(522, 'Dy. Superintendent '),"+
				"(523, 'Dy. Superintendent of Customs'),"+
				"(524, 'Dy./ Asst. Commissioner'),"+
				"(525, 'Dy./ Asst. Conservator'),"+
				"(526, 'Dy./ Asst. Registrar General'),"+
				"(527, 'Dy./ Asst. Registrar of companies'),"+
				"(528, 'Dy./Asst. Immigration & Emigration Controller'),"+
				"(529, 'Dy./Asst. Manager'),"+
				"(530, 'Dy.Asst. Director'),"+
				"(531, 'Dy.Author/ Asst. Author'),"+
				"(532, 'Dy.Chief Engineer'),"+
				"(533, 'Dy.Commissioner for Work. Compensation'),"+
				"(534, 'Dy.Director of Customs'),"+
				"(535, 'Dy.Foods & Beverages Manager'),"+
				"(536, 'Dy.Inspector General of Police'),"+
				"(537, 'Dy.Legal Draughtsman'),"+
				"(538, 'Dy.Librarian'),"+
				"(539, 'Dy.Permanent Representative'),"+
				"(540, 'Dy.Principal Officer'),"+
				"(541, 'Dy.Secretary'),"+
				"(542, 'ECG Recordist'),"+
				"(543, 'Economic Asst.'),"+
				"(544, 'Economist'),"+
				"(545, 'Editor'),"+
				"(546, 'Editor of Hansard'),"+
				"(547, 'Editorial Asst.'),"+
				"(548, 'Education & Publication Asst.'),"+
				"(549, 'Education & Research Asst.'),"+
				"(550, 'Education & Training Officer'),"+
				"(551, 'Education Officer'),"+
				"(552, 'Education Promotion Officer'),"+
				"(553, 'EEG Recordist'),"+
				"(554, 'Electric Technician'),"+
				"(555, 'Electrical Fitter'),"+
				"(556, 'Electrical Helper'),"+
				"(557, 'Electrical Labourer'),"+
				"(558, 'Electrical Maintenance Technician'),"+
				"(559, 'Electrical Mechanic'),"+
				"(560, 'Electrician'),"+
				"(561, 'Electrician/Technical Officer'),"+
				"(562, 'Electro Cardiographic Recorder'),"+
				"(563, 'Electro Medical Technician'),"+
				"(564, 'Enforcement Asst.'),"+
				"(565, 'Enforcement Officer'),"+
				"(566, 'Engine Driver'),"+
				"(567, 'Engine Driver Asst.'),"+
				"(568, 'Engineer'),"+
				"(569, 'Engineering Asst.'),"+
				"(570, 'Engineering Materials Superintendent'),"+
				"(571, 'Enginner'),"+
				"(572, 'Entomological Assistant'),"+
				"(573, 'Entomological Asst.'),"+
				"(574, 'Entomological Field Attendant'),"+
				"(575, 'Entomologist'),"+
				"(576, 'Entrepreneurship Devp. Training Officer'),"+
				"(577, 'Env. Mgt.Officer'),"+
				"(578, 'Environment Tourist Asst.'),"+
				"(579, 'Epigraphic & Numeric Officer'),"+
				"(580, 'Epigraphical Collector'),"+
				"(581, 'Epigraphy Asst.'),"+
				"(582, 'Escavator Machine Aide'),"+
				"(583, 'Establishment Asst.'),"+
				"(584, 'Evaluation Officer'),"+
				"(585, 'Examiner'),"+
				"(586, 'Excavation & Museum Asst.'),"+
				"(587, 'Excavation & Museum Officer'),"+
				"(588, 'Excavation & Museum Sevaka'),"+
				"(589, 'Excavation Asst.'),"+
				"(590, 'Excavation Officer'),"+
				"(591, 'Excavator Operator'),"+
				"(592, 'Excise Commissioner'),"+
				"(593, 'Excise Guard / Corporal'),"+
				"(594, 'Excise Inspector'),"+
				"(595, 'Excise Sergeant'),"+
				"(596, 'Excise Sergeant Major'),"+
				"(597, 'Excise Superintendent'),"+
				"(598, 'Excise Tax Officer'),"+
				"(599, 'Executioner'),"+
				"(600, 'Executive Asst.'),"+
				"(601, 'Executive Chef'),"+
				"(602, 'Executive House-keeper'),"+
				"(603, 'Executive/ Personnel Officer'),"+
				"(604, 'Exhibition Planning Officer'),"+
				"(605, 'Experimental Officer'),"+
				"(606, 'Exploration Asst.'),"+
				"(607, 'Exploration Officer'),"+
				"(608, 'Explosive Controller'),"+
				"(609, 'Extension Officer'),"+
				"(610, 'Factory Engineer'),"+
				"(611, 'Farm Clerk'),"+
				"(612, 'Farm Labourer'),"+
				"(613, 'Farm Manager'),"+
				"(614, 'Farm Service Asst.'),"+
				"(615, 'Fiber Glass Technician'),"+
				"(616, 'Field Asst.'),"+
				"(617, 'Field Coordinator'),"+
				"(618, 'Field Instructor'),"+
				"(619, 'Field Labourer'),"+
				"(620, 'Field Officer'),"+
				"(621, 'Field Watcher'),"+
				"(622, 'Film Demonstrator'),"+
				"(623, 'Finance Analyst'),"+
				"(624, 'Finance Asst.'),"+
				"(625, 'Finance Officer'),"+
				"(626, 'Financial & Cost Asst.'),"+
				"(627, 'Finger Print Inspecting Officer'),"+
				"(628, 'Finishing Asst.'),"+
				"(629, 'First Aid Attendent'),"+
				"(630, 'Fiscal Keeper'),"+
				"(631, 'Fiscal Peon'),"+
				"(632, 'Fisheries Inspector'),"+
				"(633, 'Fisheries Licence Officer'),"+
				"(634, 'Fisheries Social Devevelopment Asst.'),"+
				"(635, 'Fitter'),"+
				"(636, 'Fittler'),"+
				"(637, 'Floating Craft Mechanic'),"+
				"(638, 'Food & Drugs Inspector'),"+
				"(639, 'Food Commissioner'),"+
				"(640, 'Foods & Beverages Asst.'),"+
				"(641, 'Foods & Beverages Manager'),"+
				"(642, 'Foods & Beverages Supervisor'),"+
				"(643, 'Foreman'),"+
				"(644, 'Foreman Orthopaedic Workshop'),"+
				"(645, 'Foreman/ Asst. Chief Printing Editor'),"+
				"(646, 'Forest Extention Officer'),"+
				"(647, 'Forest Field Asst.'),"+
				"(648, 'Forester/ Asst. Division. Forest Officer'),"+
				"(649, 'Fork Lift & Lister Truck Operator'),"+
				"(650, 'Fork Lift Driver'),"+
				"(651, 'Fork Lift Operator'),"+
				"(652, 'Forman'),"+
				"(653, 'Forman - Biomedical'),"+
				"(654, 'Fumigator'),"+
				"(655, 'Fund P Officer'),"+
				"(656, 'Furniture Polisher'),"+
				"(657, 'Garage Serviceman'),"+
				"(658, 'Garden Conductor'),"+
				"(659, 'Garden Controller'),"+
				"(660, 'Garden Devp. Asst.'),"+
				"(661, 'Garden Forman'),"+
				"(662, 'Garden Labourer'),"+
				"(663, 'Garden Maintenance Asst.'),"+
				"(664, 'Garden Supervisor'),"+
				"(665, 'Garden Supervisor/ Kangani'),"+
				"(666, 'Gardner'),"+
				"(667, 'Gardner Cum Watcher'),"+
				"(668, 'Gas Cutter'),"+
				"(669, 'Gateman'),"+
				"(670, 'General Manager'),"+
				"(671, 'Generator Operator'),"+
				"(672, 'Geological Information System Repoter'),"+
				"(673, 'Geology Asst.'),"+
				"(674, 'Geo-Technician Officer'),"+
				"(675, 'Giletine Mechine Operator'),"+
				"(676, 'Gold Glider'),"+
				"(677, 'Golf Groundsman'),"+
				"(678, 'Government Analyst'),"+
				"(679, 'Government Engineering and Ship Surveyor'),"+
				"(680, 'Government Printer'),"+
				"(681, 'Govt. Senior Asst./ Asst. Examiner of Questioned Documents'),"+
				"(682, 'Govt.Examiner of Questioned Documents'),"+
				"(683, 'Graduate Appointments'),"+
				"(684, 'Graduate Trainees'),"+
				"(685, 'Grain Surveyor'),"+
				"(686, 'Grama Niladari'),"+
				"(687, 'Greaser'),"+
				"(688, 'Ground Boy'),"+
				"(689, 'Groundman'),"+
				"(690, 'Group Care Taker'),"+
				"(691, 'Guard'),"+
				"(692, 'Guard Inspector Booking'),"+
				"(693, 'Guardian'),"+
				"(694, 'Guards Inspector'),"+
				"(695, 'Handicapped Devp. Officer'),"+
				"(696, 'Handyman'),"+
				"(697, 'Hardware/Software Tech.'),"+
				"(698, 'Head quarters Executive Officer'),"+
				"(699, 'Head Teacher Center and Senior Lecture'),"+
				"(700, 'Head Warehouseman'),"+
				"(701, 'Health Driver'),"+
				"(702, 'Health Education Officer'),"+
				"(703, 'Health Management Asst.'),"+
				"(704, 'Health Overseer'),"+
				"(705, 'Heavy Duty Labourer'),"+
				"(706, 'Helpdesk Coordinator'),"+
				"(707, 'Helper'),"+
				"(708, 'Helper - Media Unit'),"+
				"(709, 'High Commissioner'),"+
				"(710, 'High Pressure Machine Operator'),"+
				"(711, 'Hindu Piligrims Rest Guardian'),"+
				"(712, 'Holiday Bungalow Keeper'),"+
				"(713, 'Hood Maker & Upholsterer'),"+
				"(714, 'Hospital Aide'),"+
				"(715, 'Hospital Overseer'),"+
				"(716, 'Hostal Keeper'),"+
				"(717, 'Hostal Labourer'),"+
				"(718, 'Hostel Matron'),"+
				"(719, 'Hostel Warden'),"+
				"(720, 'Hostel Worden'),"+
				"(721, 'House Keeper'),"+
				"(722, 'House Keeping Asst.'),"+
				"(723, 'House Maid'),"+
				"(724, 'House Warden'),"+
				"(725, 'Housekeeping Supervisor'),"+
				"(726, 'Housing Commissioner'),"+
				"(727, 'Housing Dev. Asst.'),"+
				"(728, 'Human Resources Asst.'),"+
				"(729, 'Human Resources Devp. Asst.'),"+
				"(730, 'Human Resources Devp. Officer'),"+
				"(731, 'Human Resources Promotion Asst.'),"+
				"(732, 'Hydro Controller'),"+
				"(733, 'Hydro Graphic Surveyor'),"+
				"(734, 'Hydrological Asst.'),"+
				"(735, 'Hydrological Data Superintendent'),"+
				"(736, 'Hydrological Field Asst.'),"+
				"(737, 'Hydrological Field Superintendent'),"+
				"(738, 'Hydrological Meassurement Aide'),"+
				"(739, 'Hydrological Superintendent'),"+
				"(740, 'ICT Assistant'),"+
				"(741, 'ICT Officer'),"+
				"(742, 'Immigration & Emigration Controller'),"+
				"(743, 'Immigration & Emigration Controller General'),"+
				"(744, 'Import and Export Controller'),"+
				"(745, 'Indexing Officer'),"+
				"(746, 'Industrial Devp. Asst.'),"+
				"(747, 'Industrial Forman'),"+
				"(748, 'Industrial Inspector'),"+
				"(749, 'Industrial Supervisor'),"+
				"(750, 'Informal Education Instructor'),"+
				"(751, 'Information & Communication Technology Asst.'),"+
				"(752, 'Information & communication technology officer'),"+
				"(753, 'Information Asst.'),"+
				"(754, 'Information Officer'),"+
				"(755, 'Information Tech.Officer'),"+
				"(756, 'Information Technology Asst.'),"+
				"(757, 'Information Technology Officer'),"+
				"(758, 'Inspector'),"+
				"(759, 'Inspector General of Police'),"+
				"(760, 'Inspector of Companies'),"+
				"(761, 'Inspector of Customs'),"+
				"(762, 'Inspector of Meas. Unit'),"+
				"(763, 'Inspector of Measures Services & Devices'),"+
				"(764, 'Inspector of Police'),"+
				"(765, 'Inspector of Watchers'),"+
				"(766, 'Inspector of Work'),"+
				"(767, 'Institute Devp. Officer'),"+
				"(768, 'Instructor'),"+
				"(769, 'Instrument Artificer'),"+
				"(770, 'Instrument Repairer'),"+
				"(771, 'Intellectual Property Officer'),"+
				"(772, 'Intellectual Property Research Asst.'),"+
				"(773, 'intellectual Property Technical Asst.'),"+
				"(774, 'Internal Audit Asst.'),"+
				"(775, 'Internal Auditor'),"+
				"(776, 'Interpreter'),"+
				"(777, 'Investigation & Devp. Asst.'),"+
				"(778, 'Investigation Aide'),"+
				"(779, 'Investigation Asst.'),"+
				"(780, 'Investigation Officer'),"+
				"(781, 'Irrigation Director'),"+
				"(782, 'IT Asst.'),"+
				"(783, 'IT Officer'),"+
				"(784, 'Jailor'),"+
				"(785, 'Junior Asst. Librarian'),"+
				"(786, 'Junior Clerk'),"+
				"(787, 'Junior Radio Officer'),"+
				"(788, 'Kangani'),"+
				"(789, 'Kitchen Asst.'),"+
				"(790, 'Kitchen Stewarding Supervisor'),"+
				"(791, 'KKS'),"+
				"(792, 'Lab Orderly'),"+
				"(793, 'Label Writer'),"+
				"(794, 'Laboratory Aide'),"+
				"(795, 'Laboratory Asst.'),"+
				"(796, 'Laboratory Attendent'),"+
				"(797, 'Laboratory Labourer'),"+
				"(798, 'Laboratory Technician'),"+
				"(799, 'Labour Officer'),"+
				"(800, 'Labour Tribunal President Asst.'),"+
				"(801, 'Labour Tribunals - Clerk'),"+
				"(802, 'Labour Tribunals - Interpreter'),"+
				"(803, 'Labour Tribunals - Stenographer'),"+
				"(804, 'Labour Tribunals - Typist'),"+
				"(805, 'Labour Wharf'),"+
				"(806, 'Labourer'),"+
				"(807, 'Laminating Machine Operator'),"+
				"(808, 'Lampman'),"+
				"(809, 'Land Commissioner'),"+
				"(810, 'Land Commissioner General'),"+
				"(811, 'Land Scaper'),"+
				"(812, 'Land Use Planning Asst.'),"+
				"(813, 'Land Use Superintendent'),"+
				"(814, 'Land/Title/ District Registrar'),"+
				"(815, 'Landscape Architect'),"+
				"(816, 'Language Consultant'),"+
				"(817, 'Language Contact Co-ordinator'),"+
				"(818, 'Language Coordinator'),"+
				"(819, 'Language Editor'),"+
				"(820, 'Language Laboratary Asst.'),"+
				"(821, 'Lathe Machine Operator'),"+
				"(822, 'Latheman'),"+
				"(823, 'Laundry Machine Operator'),"+
				"(824, 'Laundry Supervisor'),"+
				"(825, 'Lawnmower Operator'),"+
				"(826, 'Lay Maker'),"+
				"(827, 'Leader of the Opposition'),"+
				"(828, 'Leading Cook'),"+
				"(829, 'Leading Foods & Beverages Asst.'),"+
				"(830, 'Leather Worker'),"+
				"(831, 'Lecturer'),"+
				"(832, 'Legal Advisor'),"+
				"(833, 'Legal Analyst'),"+
				"(834, 'Legal Asst.'),"+
				"(835, 'Legal Consultant'),"+
				"(836, 'Legal Draughtsman'),"+
				"(837, 'Legal Officer'),"+
				"(838, 'Liave Food Collector'),"+
				"(839, 'Librarian'),"+
				"(840, 'Library Asst.'),"+
				"(841, 'Library Attendant'),"+
				"(842, 'Library Officer'),"+
				"(843, 'Lift Operator'),"+
				"(844, 'Lift Technician'),"+
				"(845, 'Lighting Aide'),"+
				"(846, 'Lighting Technician'),"+
				"(847, 'Limb Maker'),"+
				"(848, 'Line Room Attendant'),"+
				"(849, 'Linesman'),"+
				"(850, 'Litho Printer'),"+
				"(851, 'Litho Printer '),"+
				"(852, 'Litho Printer & Machine Minder'),"+
				"(853, 'Lito Machine Operator'),"+
				"(854, 'Livestock Asst.'),"+
				"(855, 'Livestock Devp. Officer'),"+
				"(856, 'Livestock Economic Scientist'),"+
				"(857, 'Livestock Extention Specialist'),"+
				"(858, 'Livestock Investigation Officer'),"+
				"(859, 'Livestock Research Officer'),"+
				"(860, 'Loader & Lorry Cleaner'),"+
				"(861, 'Loader Backhoe Machine Operator'),"+
				"(862, 'Loader Machine Aide'),"+
				"(863, 'Loader Operator'),"+
				"(864, 'Lorry Asst.'),"+
				"(865, 'Lorry Attendent'),"+
				"(866, 'Lorry Cleaner'),"+
				"(867, 'Lorry Driver'),"+
				"(868, 'Lorry Drivers Helper'),"+
				"(869, 'Lorry Helper'),"+
				"(870, 'Lorry/ Bus Cleaner'),"+
				"(871, 'Machanical Superintendent'),"+
				"(872, 'Machine Aide'),"+
				"(873, 'Machine Attendent'),"+
				"(874, 'Machine Minder'),"+
				"(875, 'Machine Oper.'),"+
				"(876, 'Machine Operator'),"+
				"(877, 'Mahout'),"+
				"(878, 'Mainitenance Asst.'),"+
				"(879, 'Maintenance & Operation Labourer'),"+
				"(880, 'Maintenance Officer'),"+
				"(881, 'Maintenance Supervisor'),"+
				"(882, 'Management & Finance Asst.'),"+
				"(883, 'Management Asst.'),"+
				"(884, 'Manager'),"+
				"(885, 'Mangement Asst. for Fisheries Activities'),"+
				"(886, 'Map Mounter & Book Binder'),"+
				"(887, 'Map Production Officer'),"+
				"(888, 'Mapping Technological Officer'),"+
				"(889, 'Marine Engineer'),"+
				"(890, 'Marine Engineering Asst.'),"+
				"(891, 'Marine Env. Officer'),"+
				"(892, 'Marketing & Supply Asst.'),"+
				"(893, 'Marketing Officer'),"+
				"(894, 'Mason'),"+
				"(895, 'Masseur'),"+
				"(896, 'Material Superintendent'),"+
				"(897, 'Matron'),"+
				"(898, 'Measurement Attendent'),"+
				"(899, 'Measuring Aide'),"+
				"(900, 'Mechanic'),"+
				"(901, 'Mechanical Engineer'),"+
				"(902, 'Mechanical Foreman'),"+
				"(903, 'Mechanical Section Employee'),"+
				"(904, 'Mechanical Superintendent'),"+
				"(905, 'Mechanist'),"+
				"(906, 'Mechanist/Metal Worker'),"+
				"(907, 'Mechine Helper'),"+
				"(908, 'Mechine Operator'),"+
				"(909, 'Media Asst.'),"+
				"(910, 'Media Cordinator'),"+
				"(911, 'Media Officer'),"+
				"(912, 'Media Secretary'),"+
				"(913, 'Medical Administrator (DTY)'),"+
				"(914, 'Medical Administrator (SNR)'),"+
				"(915, 'Medical Consultant'),"+
				"(916, 'Medical Laboratory Technologist'),"+
				"(917, 'Medical Officer'),"+
				"(918, 'Medical Record Assistant'),"+
				"(919, 'Medical Record Officer'),"+
				"(920, 'Medical Records Officer'),"+
				"(921, 'Medical Supplies Assistant'),"+
				"(922, 'Menum Sahayaka'),"+
				"(923, 'Mess Boy'),"+
				"(924, 'Mess Waiter'),"+
				"(925, 'Messenger'),"+
				"(926, 'Meteorological Officer/ Observer'),"+
				"(927, 'Meteorology Communication Officer'),"+
				"(928, 'Meterology Experimental Officer'),"+
				"(929, 'Mettress Worker'),"+
				"(930, 'Micro Photographer'),"+
				"(931, 'Midwife'),"+
				"(932, 'Minor Supervisor'),"+
				"(933, 'Motor Bicycle Mechanic'),"+
				"(934, 'Motor Electrical Mechanic'),"+
				"(935, 'Motor Grader Aide'),"+
				"(936, 'Motor Grader Operator'),"+
				"(937, 'Motor Mechanic'),"+
				"(938, 'Motor Technician'),"+
				"(939, 'Motor Vehicle Cleaner'),"+
				"(940, 'Motor Vehicle Driver'),"+
				"(941, 'Motor Vehicle Inspector'),"+
				"(942, 'Museum Aide'),"+
				"(943, 'Musician'),"+
				"(944, 'National Coordinator-Disaster Management'),"+
				"(945, 'National Integration Cordinator'),"+
				"(946, 'National Integration Promotion (Sahayaka)'),"+
				"(947, 'National Integration Promotion Asst.'),"+
				"(948, 'Navigator'),"+
				"(949, 'Negative Keeper'),"+
				"(950, 'News Writer'),"+
				"(951, 'Nursaryman'),"+
				"(952, 'Nurse'),"+
				"(953, 'Nursery Manager'),"+
				"(954, 'Nurseryman'),"+
				"(955, 'Nursing Officer'),"+
				"(956, 'Nutritionist'),"+
				"(957, 'O.E.S. Posts'),"+
				"(958, 'O.I.C. Training Camp'),"+
				"(959, 'Occupational Therapist'),"+
				"(960, 'Office Labourer'),"+
				"(961, 'Office Watcher'),"+
				"(962, 'Officer-in-Charge'),"+
				"(963, 'Officer-in-charge of Hostels'),"+
				"(964, 'Offset Litho Machine Operator'),"+
				"(965, 'Offset Machine Operator'),"+
				"(966, 'Operational Officer'),"+
				"(967, 'Operator'),"+
				"(968, 'Ophthalmic Technologist'),"+
				"(969, 'Organization & Method Asst.'),"+
				"(970, 'Organization Asst.'),"+
				"(971, 'Orthopaedic Technician'),"+
				"(972, 'Orthopaedic Workshop Manager'),"+
				"(973, 'Orthoptist'),"+
				"(974, 'Other DPL Appointments'),"+
				"(975, 'Oversear'),"+
				"(976, 'Overseer'),"+
				"(977, 'Overseer / Chief Survey Asst.'),"+
				"(978, 'Packer'),"+
				"(979, 'Page Layout Designer'),"+
				"(980, 'Painter'),"+
				"(981, 'Pantry Labourer'),"+
				"(982, 'Paracitalogist'),"+
				"(983, 'Park Labour'),"+
				"(984, 'Parliamentary Commissioner (Ombudsman)'),"+
				"(985, 'Parliamentary Media Officer'),"+
				"(986, 'Parliamentary Services Asst.'),"+
				"(987, 'Pautry Labour'),"+
				"(988, 'Pension Officer'),"+
				"(989, 'Peon'),"+
				"(990, 'Perfomance Appraisal Asst.'),"+
				"(991, 'Perfunist'),"+
				"(992, 'Personal Asst.'),"+
				"(993, 'Personal Secretary'),"+
				"(994, 'Personal Security Officer'),"+
				"(995, 'Pharmaceutical Analyst'),"+
				"(996, 'Pharmacist'),"+
				"(997, 'Pharmacy Labourer'),"+
				"(998, 'Photo Laboratory Asst.'),"+
				"(999, 'Photocopy Machine Operator'),"+
				"(1000, 'Photogrametrist Technician'),"+
				"(1001, 'Photogrametrist Technician (Special)'),"+
				"(1002, 'Photogrammetrist'),"+
				"(1003, 'Photographer'),"+
				"(1004, 'Photographer - Media'),"+
				"(1005, 'Photographer - Technical'),"+
				"(1006, 'Photographer Aide'),"+
				"(1007, 'Photographic Assistant'),"+
				"(1008, 'Physicist'),"+
				"(1009, 'Physiotherapist'),"+
				"(1010, 'Pinting Supervisor'),"+
				"(1011, 'Pipe Fitter'),"+
				"(1012, 'Pipe Line Cleaner'),"+
				"(1013, 'Plan - Keeper'),"+
				"(1014, 'Plan Implementation Officer'),"+
				"(1015, 'Plan Keeper'),"+
				"(1016, 'Plan Printer'),"+
				"(1017, 'Plan Printer Helper'),"+
				"(1018, 'Plan Repairer'),"+
				"(1019, 'Plan Typographer'),"+
				"(1020, 'Planning and Programme Officer'),"+
				"(1021, 'Planning and Programming Asst.'),"+
				"(1022, 'Planning Asst.'),"+
				"(1023, 'Planning Finance Asst.'),"+
				"(1024, 'Planning Officer'),"+
				"(1025, 'Plant Attendant'),"+
				"(1026, 'Plant Mechanic'),"+
				"(1027, 'Plant Operator'),"+
				"(1028, 'Plant Supervisor'),"+
				"(1029, 'Plantation Community Communication Facilitator'),"+
				"(1030, 'Plantation Monitoring Officer'),"+
				"(1031, 'Plate Maker'),"+
				"(1032, 'Plate Maker Operator'),"+
				"(1033, 'Play Grounds Asst.'),"+
				"(1034, 'Plumber'),"+
				"(1035, 'Plumber / Pump Machine Operator'),"+
				"(1036, 'PMAS (Personal Asst.s/Stenographers)'),"+
				"(1037, 'PMAS/Clerk'),"+
				"(1038, 'Pointsman'),"+
				"(1039, 'Police Constable'),"+
				"(1040, 'Police Sergeant'),"+
				"(1041, 'Policeman'),"+
				"(1042, 'Policy and Planning Asst.'),"+
				"(1043, 'Polisher'),"+
				"(1044, 'Pool Attendant'),"+
				"(1045, 'Port State Controller (Deck)'),"+
				"(1046, 'Port State Controller (Engineering)'),"+
				"(1047, 'Post Eva. & Moni. Asst.'),"+
				"(1048, 'Postal Aide'),"+
				"(1049, 'Postal Services Officer'),"+
				"(1050, 'Postmaster General'),"+
				"(1051, 'Pre Childhood Devp. Asst.'),"+
				"(1052, 'Pre School Teacher'),"+
				"(1053, 'Precess Hand'),"+
				"(1054, 'Premisses in charge'),"+
				"(1055, 'President'),"+
				"(1056, 'Press Inspector'),"+
				"(1057, 'Press Officer'),"+
				"(1058, 'Preventive Guard'),"+
				"(1059, 'Preventive Sergeant'),"+
				"(1060, 'Principal'),"+
				"(1061, 'Principal - Audiology Technicians School'),"+
				"(1062, 'Principal - ECG School'),"+
				"(1063, 'Principal - MLT School'),"+
				"(1064, 'Principal - Nursing'),"+
				"(1065, 'Principal - Prosthetists and Orthotists School'),"+
				"(1066, 'Principal - Radiography School'),"+
				"(1067, 'Principal - School Dental Therapist Training School'),"+
				"(1068, 'Principal Officer'),"+
				"(1069, 'Principal Public Health Inspector'),"+
				"(1070, 'Principal Scientist'),"+
				"(1071, 'Printer'),"+
				"(1072, 'Printing Designer'),"+
				"(1073, 'Printing Foreman'),"+
				"(1074, 'Printing Manager'),"+
				"(1075, 'Printing Mechanic'),"+
				"(1076, 'Printing Operator'),"+
				"(1077, 'Printing Technician'),"+
				"(1078, 'Printing Works Aide'),"+
				"(1079, 'Prison Superintendent'),"+
				"(1080, 'Private Secretary'),"+
				"(1081, 'Private Secretary for Ambassador'),"+
				"(1082, 'Probation Officer'),"+
				"(1083, 'Procument Asst.'),"+
				"(1084, 'Procument Officer'),"+
				"(1085, 'Procurement Excecutive'),"+
				"(1086, 'Production Asst.'),"+
				"(1087, 'Production Helper'),"+
				"(1088, 'Production Monitoring & Planning Asst.'),"+
				"(1089, 'Production Officer'),"+
				"(1090, 'Production Work Asst.'),"+
				"(1091, 'Productivity Devp. Asst.'),"+
				"(1092, 'Productivity Promotion Asst.'),"+
				"(1093, 'Programme Asst.'),"+
				"(1094, 'Programme Manager'),"+
				"(1095, 'Programme Officer'),"+
				"(1096, 'Programme Planning Asst.'),"+
				"(1097, 'Programmer'),"+
				"(1098, 'Progress & Devp. Asst.'),"+
				"(1099, 'Progress Asst.'),"+
				"(1100, 'Progress Control Officer'),"+
				"(1101, 'Progress Tracer'),"+
				"(1102, 'Project Aide'),"+
				"(1103, 'Project Asst.'),"+
				"(1104, 'Project Devp. Asst.'),"+
				"(1105, 'Project Implementation Asst.'),"+
				"(1106, 'Project Officer'),"+
				"(1107, 'Projection & Record Room Operator'),"+
				"(1108, 'Proof Reader'),"+
				"(1109, 'Prosthetist and Orthotist'),"+
				"(1110, 'Protocol Asst.'),"+
				"(1111, 'Protocol Officer'),"+
				"(1112, 'Province / Dy.. Survey General'),"+
				"(1113, 'Provincial Director'),"+
				"(1114, 'PRUN (Permanent Representative of United Nations)'),"+
				"(1115, 'Psychiatric Social Worker'),"+
				"(1116, 'Psychological Councellor'),"+
				"(1117, 'Psychologist'),"+
				"(1118, 'Public Coordinating Assistant'),"+
				"(1119, 'Public Health Field Officer'),"+
				"(1120, 'Public Health Inspector'),"+
				"(1121, 'Public Health Laboratory Technician'),"+
				"(1122, 'Public Health Nursing Sister'),"+
				"(1123, 'Public Health Veterinary Officer'),"+
				"(1124, 'Public Management Asst.'),"+
				"(1125, 'Public Relations Officer'),"+
				"(1126, 'Public Trustee'),"+
				"(1127, 'Public Veterinary Health Assistant'),"+
				"(1128, 'Public/ Health Management Asst.'),"+
				"(1129, 'Publication Asst.'),"+
				"(1130, 'Publication Officer'),"+
				"(1131, 'Publicity Asst.'),"+
				"(1132, 'Publicity Officer'),"+
				"(1133, 'Pump Attendant'),"+
				"(1134, 'Pump Operator'),"+
				"(1135, 'Purchasing Officer'),"+
				"(1136, 'Quality Assurance Associate'),"+
				"(1137, 'Quality Assurance Scientist'),"+
				"(1138, 'Quality Control Asst'),"+
				"(1139, 'Quality Control Officer'),"+
				"(1140, 'Quantity Surveying Asst.'),"+
				"(1141, 'Quantity Surveyor'),"+
				"(1142, 'Radio Programme Producer'),"+
				"(1143, 'Radiographer'),"+
				"(1144, 'Railway Station Attendant'),"+
				"(1145, 'Range Maker'),"+
				"(1146, 'Range Warden'),"+
				"(1147, 'Ranger Forest Officer'),"+
				"(1148, 'Rattan Warden'),"+
				"(1149, 'Rattan Weaver'),"+
				"(1150, 'Rattaner & Polisher'),"+
				"(1151, 'Receiptionist'),"+
				"(1152, 'Receiving Officer'),"+
				"(1153, 'Reception & Sales Officer'),"+
				"(1154, 'Receptionist'),"+
				"(1155, 'Record Indexing Officer'),"+
				"(1156, 'Record Investigation Officer'),"+
				"(1157, 'Record Keeper'),"+
				"(1158, 'Recorder'),"+
				"(1159, 'Recording Asst.'),"+
				"(1160, 'Recording Supervisor'),"+
				"(1161, 'Recordist'),"+
				"(1162, 'Record-keeper'),"+
				"(1163, 'Rediator Mechanic'),"+
				"(1164, 'Refrigerator Mechanic'),"+
				"(1165, 'Regional Conservator'),"+
				"(1166, 'Regional Coordinator'),"+
				"(1167, 'Regional Excavator & Museum Officer'),"+
				"(1168, 'Regional Explorer'),"+
				"(1169, 'Regional Maintenance Manager'),"+
				"(1170, 'Regional Valuar'),"+
				"(1171, 'Registar'),"+
				"(1172, 'Registerd Substitute'),"+
				"(1173, 'Registered Medical Officer'),"+
				"(1174, 'Registrar'),"+
				"(1175, 'Registrar General'),"+
				"(1176, 'Registrar General of Companies'),"+
				"(1177, 'Registrar of Companies'),"+
				"(1178, 'Registrar of Pesticides'),"+
				"(1179, 'Rehabilitation Aide'),"+
				"(1180, 'Relief Sister'),"+
				"(1181, 'Remote Sensing Technician'),"+
				"(1182, 'Report Keeper'),"+
				"(1183, 'Research & Development Officer'),"+
				"(1184, 'Research & Devp. Asst.'),"+
				"(1185, 'Research Asst.'),"+
				"(1186, 'Research Officer'),"+
				"(1187, 'Research Sub Asst.'),"+
				"(1188, 'Residence Attendant'),"+
				"(1189, 'Resident Project Manager'),"+
				"(1190, 'Resort Keeper'),"+
				"(1191, 'Resources Asst.'),"+
				"(1192, 'Resturant Manager'),"+
				"(1193, 'Rigger'),"+
				"(1194, 'RMO / AMO'),"+
				"(1195, 'Ronio Machine Operator'),"+
				"(1196, 'Room Boy'),"+
				"(1197, 'RSPHNO'),"+
				"(1198, 'Rubber Devp. Officer'),"+
				"(1199, 'Running Bunglow Asst.'),"+
				"(1200, 'Rupawahini Service Asst.'),"+
				"(1201, 'Rural Sociologist'),"+
				"(1202, 'S.L.Eng. Service'),"+
				"(1203, 'Sail Maker'),"+
				"(1204, 'Sales Asst.'),"+
				"(1205, 'Sales Manager'),"+
				"(1206, 'Saloon Car Attendant'),"+
				"(1207, 'Sanitary Labourer'),"+
				"(1208, 'Saukyaya Karyaya Sahayaka'),"+
				"(1209, 'Saukyaya Karyaya Sahayaka (Jnr)'),"+
				"(1210, 'Sawyer Mechanic'),"+
				"(1211, 'School Dental Therapist'),"+
				"(1212, 'School Labourer'),"+
				"(1213, 'Science and Technology Officer'),"+
				"(1214, 'Science and Technology Planning Asst.'),"+
				"(1215, 'Scientific Officer'),"+
				"(1216, 'Seamster'),"+
				"(1217, 'Seamstress'),"+
				"(1218, 'Secretary'),"+
				"(1219, 'Secretary Bilingual'),"+
				"(1220, 'Secretary Cum Translator'),"+
				"(1221, 'Secretary General of Parliament'),"+
				"(1222, 'Secretary to the President'),"+
				"(1223, 'Secretary to the Prime Minister'),"+
				"(1224, 'Secretary/Chief Executive Officer'),"+
				"(1225, 'Sector Commander'),"+
				"(1226, 'Security'),"+
				"(1227, 'Security Asst.'),"+
				"(1228, 'Security Consultant'),"+
				"(1229, 'Security Guard'),"+
				"(1230, 'Security Methods Demonstrator'),"+
				"(1231, 'Security Officer'),"+
				"(1232, 'Security/ Asst. Security Officer'),"+
				"(1233, 'Seed Man'),"+
				"(1234, 'Seed Technician'),"+
				"(1235, 'Sen.Asst.Commissioner'),"+
				"(1236, 'Senior Advisor'),"+
				"(1237, 'Senior Assistant Director'),"+
				"(1238, 'Senior Asst. Secretary'),"+
				"(1239, 'Senior Asst.Legal Draughtsman'),"+
				"(1240, 'Senior Authorised Officer'),"+
				"(1241, 'Senior Bio Medical Engineer'),"+
				"(1242, 'Senior Civil Engineering Meterial Surveyor'),"+
				"(1243, 'Senior Community Correction Officer'),"+
				"(1244, 'Senior Curator'),"+
				"(1245, 'Senior Dy Govt. Printer'),"+
				"(1246, 'Senior Dy.Registrar General'),"+
				"(1247, 'Senior Dy.Survey General'),"+
				"(1248, 'Senior Economist'),"+
				"(1249, 'Senior Hydrological Asst.'),"+
				"(1250, 'Senior Hydrological Field Asst.'),"+
				"(1251, 'Senior Legal Advisor'),"+
				"(1252, 'Senior Legal Analyst'),"+
				"(1253, 'Senior Legal Officer'),"+
				"(1254, 'Senior Librarian'),"+
				"(1255, 'Senior Mechanic'),"+
				"(1256, 'Senior Mechanical Superintendent'),"+
				"(1257, 'Senior Meteorological Officer'),"+
				"(1258, 'Senior Motor Vehicle Inspector'),"+
				"(1259, 'Senior Public Health Inspector'),"+
				"(1260, 'Senior Public Health Midwife'),"+
				"(1261, 'Senior Public Health Nursing Tutor'),"+
				"(1262, 'Senior Radio Officer'),"+
				"(1263, 'Senior Research Asst.'),"+
				"(1264, 'Senior Research Officer'),"+
				"(1265, 'Senior Soil Cartographer'),"+
				"(1266, 'Senior Soil Surveyor'),"+
				"(1267, 'Senior Statistician'),"+
				"(1268, 'Senior Superintendent of Police'),"+
				"(1269, 'Senior Survey Superintendent'),"+
				"(1270, 'Senior Tutor Audiology Technicians School'),"+
				"(1271, 'Senior Tutor ECG School'),"+
				"(1272, 'Senior Tutor MLT'),"+
				"(1273, 'Senior Tutor Nursing'),"+
				"(1274, 'Senior Tutor Ophthalmic Technologist'),"+
				"(1275, 'Senior Tutor Pharmacist'),"+
				"(1276, 'Senior Tutor Prosthetists & Orthotists'),"+
				"(1277, 'Senior Tutor Public Health Inspector'),"+
				"(1278, 'Senior Tutor Public Health Laboratory Technician School'),"+
				"(1279, 'Senior Tutor Radiography School'),"+
				"(1280, 'Senior Welfare Officer'),"+
				"(1281, 'Senior Work Supervisor'),"+
				"(1282, 'Senoir Asst. Secretary'),"+
				"(1283, 'Sergeant'),"+
				"(1284, 'Sergeant Major'),"+
				"(1285, 'Sergeant-At-Arms'),"+
				"(1286, 'Serviceman'),"+
				"(1287, 'Shipping Officer'),"+
				"(1288, 'Shoe Maker'),"+
				"(1289, 'Shop Asst.'),"+
				"(1290, 'Shop Clerk'),"+
				"(1291, 'Shroff'),"+
				"(1292, 'Shunter'),"+
				"(1293, 'Sign Language Translater'),"+
				"(1294, 'Sign Writer'),"+
				"(1295, 'Signalman'),"+
				"(1296, 'Site Manager'),"+
				"(1297, 'Skill Devp. Asst.'),"+
				"(1298, 'Skilled Labourer'),"+
				"(1299, 'SL Technological Service'),"+
				"(1300, 'Sleeping Car Attendant'),"+
				"(1301, 'SLEgS'),"+
				"(1302, 'Snr. Architect/Architect'),"+
				"(1303, 'Snr. Assessor/Assessor'),"+
				"(1304, 'Snr. Asst. Secretary to the President'),"+
				"(1305, 'Snr. Commissioner'),"+
				"(1306, 'Snr. Communication Officer'),"+
				"(1307, 'Snr. Dy. Commissioner General'),"+
				"(1308, 'Snr. Dy.Survey General (Admin)'),"+
				"(1309, 'Snr. Hansard Reporter / Hansard Reporter'),"+
				"(1310, 'Snr. Information Officer'),"+
				"(1311, 'Snr. Lecture'),"+
				"(1312, 'Snr. Meteorologist/Meteorologist'),"+
				"(1313, 'Snr. Parli.Interpreter & Parli. Interpreter'),"+
				"(1314, 'Snr. Post Superintendent'),"+
				"(1315, 'Snr. Station Master'),"+
				"(1316, 'Snr. Superintendent of Works'),"+
				"(1317, 'Snr. System Analyst/Programmer'),"+
				"(1318, 'Snr. System Operator'),"+
				"(1319, 'Snr. System Record Keeper'),"+
				"(1320, 'Snr.Asst. State Attorney'),"+
				"(1321, 'Snr.Dy.Inspector General of Police'),"+
				"(1322, 'Snr.State Counselor'),"+
				"(1323, 'Snr.Tax Officer/ Tax Officer'),"+
				"(1324, 'Snr.Telecom.& Redar Technical Officer'),"+
				"(1325, 'Social Asst.'),"+
				"(1326, 'Social Devp. Asst.'),"+
				"(1327, 'Social Services Officer'),"+
				"(1328, 'Social Welfare Officer'),"+
				"(1329, 'Social Welfare Superintendent'),"+
				"(1330, 'Sociologist'),"+
				"(1331, 'Soil Cartographer'),"+
				"(1332, 'Soil Surveyor'),"+
				"(1333, 'Solicitor General'),"+
				"(1334, 'Solid Linen Collector'),"+
				"(1335, 'Sound Controller'),"+
				"(1336, 'Sound Operator'),"+
				"(1337, 'Souse Chef'),"+
				"(1338, 'Sp Forester/ Division. Forest Officer'),"+
				"(1339, 'Special Grade Dental Technician'),"+
				"(1340, 'Special Grade ECG Recordist'),"+
				"(1341, 'Special Grade EEG Technician'),"+
				"(1342, 'Special Grade Entomological Assistant'),"+
				"(1343, 'Special Grade Food and Drug Inspector'),"+
				"(1344, 'Special Grade Midwife'),"+
				"(1345, 'Special Grade MLT'),"+
				"(1346, 'Special Grade Occupational Therapist'),"+
				"(1347, 'Special Grade Ophthalmic Technologist'),"+
				"(1348, 'Special Grade Pharmacist'),"+
				"(1349, 'Special Grade PHI'),"+
				"(1350, 'Special Grade PHLT'),"+
				"(1351, 'Special Grade Physiotherapist'),"+
				"(1352, 'Special Grade Radiographer'),"+
				"(1353, 'Special Grade School Dental Therapist'),"+
				"(1354, 'Special Laboratory Labourer'),"+
				"(1355, 'Specialist Officer (Land Use)'),"+
				"(1356, 'Speech Therapist'),"+
				"(1357, 'Sports Coach'),"+
				"(1358, 'Sports Officer'),"+
				"(1359, 'Spray Machine Operator'),"+
				"(1360, 'Spraying Labourer'),"+
				"(1361, 'Sr.Personal Asst.'),"+
				"(1362, 'Staff Asst.'),"+
				"(1363, 'Stage Electrician Aide'),"+
				"(1364, 'Stamp Clerk'),"+
				"(1365, 'Standards & Services'),"+
				"(1366, 'Standards and Services'),"+
				"(1367, 'State Attorney'),"+
				"(1368, 'State Counselor/Temp. State Counselor'),"+
				"(1369, 'Statictician'),"+
				"(1370, 'Station Master'),"+
				"(1371, 'Station Superintendent'),"+
				"(1372, 'Statistical & Research Officer'),"+
				"(1373, 'Statistical Asst.'),"+
				"(1374, 'Statistical Draughtsman'),"+
				"(1375, 'Statistical Officer'),"+
				"(1376, 'Statistical Surveys Officer'),"+
				"(1377, 'Statistician'),"+
				"(1378, 'Steel Metal Worker'),"+
				"(1379, 'Steno / typist'),"+
				"(1380, 'Stenographer'),"+
				"(1381, 'Steward'),"+
				"(1382, 'Still Camaraman'),"+
				"(1383, 'Still Cameraman'),"+
				"(1384, 'Still Photographer'),"+
				"(1385, 'Store - Watcher'),"+
				"(1386, 'Store Asst'),"+
				"(1387, 'Store Keeper'),"+
				"(1388, 'Store Labourer'),"+
				"(1389, 'Store Man'),"+
				"(1390, 'Store Services Asst.'),"+
				"(1391, 'Storekeeper'),"+
				"(1392, 'Storeman'),"+
				"(1393, 'Stores & Supply Asst'),"+
				"(1394, 'Stores Clerk'),"+
				"(1395, 'Stores Controller'),"+
				"(1396, 'Stores Keeper'),"+
				"(1397, 'Stores Labourer'),"+
				"(1398, 'Stores Officer'),"+
				"(1399, 'Stores Superintendent'),"+
				"(1400, 'Sub Inspector of Police'),"+
				"(1401, 'Sub Laboratory Asst.'),"+
				"(1402, 'Sub Postmaster'),"+
				"(1403, 'Sub Work Inspector'),"+
				"(1404, 'Summon Server & Writ Executor'),"+
				"(1405, 'Superindentent of Vaccine Production'),"+
				"(1406, 'Superintendent'),"+
				"(1407, 'Superintendent of Audit'),"+
				"(1408, 'Superintendent of Customs'),"+
				"(1409, 'Superintendent of Engineer'),"+
				"(1410, 'Superintendent of Languages Laboratary'),"+
				"(1411, 'Superintendent of Museum'),"+
				"(1412, 'Superintendent of Police'),"+
				"(1413, 'Superintendent of Post'),"+
				"(1414, 'Superintendent of Press'),"+
				"(1415, 'Superintendent of Translation'),"+
				"(1416, 'Superintendent of Work'),"+
				"(1417, 'Supervision Manager'),"+
				"(1418, 'Supervisor/Bill Clerk'),"+
				"(1419, 'Supplies Asst.'),"+
				"(1420, 'Supplies Asst. / Procurement Asst.'),"+
				"(1421, 'Supply Officer'),"+
				"(1422, 'Supt. Of Civil Est.'),"+
				"(1423, 'Surgical Appliances Maker'),"+
				"(1424, 'Survey Aide'),"+
				"(1425, 'Survey Asst.'),"+
				"(1426, 'Survey General'),"+
				"(1427, 'Survey Instrument Technician'),"+
				"(1428, 'Survey Superintendent'),"+
				"(1429, 'Surveyor'),"+
				"(1430, 'Sweeper'),"+
				"(1431, 'Swich Board Operator'),"+
				"(1432, 'Swimming Pool Asst.'),"+
				"(1433, 'Switch Board Fitter'),"+
				"(1434, 'Switch Board Operator'),"+
				"(1435, 'System Administrator'),"+
				"(1436, 'System Analyst'),"+
				"(1437, 'System Controller'),"+
				"(1438, 'System Engineer'),"+
				"(1439, 'System Operator'),"+
				"(1440, 'Systems Administrator'),"+
				"(1441, 'Systems Analyst'),"+
				"(1442, 'Systems Controller'),"+
				"(1443, 'Systems Designer'),"+
				"(1444, 'Systems Engineer'),"+
				"(1445, 'Systems Manager'),"+
				"(1446, 'Systems Operator'),"+
				"(1447, 'Systems Record Keeper'),"+
				"(1448, 'Tailor'),"+
				"(1449, 'Tanslator'),"+
				"(1450, 'Taxidermist'),"+
				"(1451, 'Teacher'),"+
				"(1452, 'Techical Asst.'),"+
				"(1453, 'Technical & Administrative Officer'),"+
				"(1454, 'Technical Aide'),"+
				"(1455, 'Technical Asst.'),"+
				"(1456, 'Technical Attendent'),"+
				"(1457, 'Technical Management Asst.'),"+
				"(1458, 'Technical Officer'),"+
				"(1459, 'Technician'),"+
				"(1460, 'Technician Asst.'),"+
				"(1461, 'Technician Attendant'),"+
				"(1462, 'Technological Management Asst.'),"+
				"(1463, 'Technology Asst.'),"+
				"(1464, 'Technology Officer'),"+
				"(1465, 'Telephone Fitter'),"+
				"(1466, 'Telephone Operator'),"+
				"(1467, 'Telephone Operator/Receptionist'),"+
				"(1468, 'Telephone Supervisor'),"+
				"(1469, 'Textile Consultant'),"+
				"(1470, 'Textile Devp. Asst.'),"+
				"(1471, 'Textile Technologist'),"+
				"(1472, 'Theater Hall Helper'),"+
				"(1473, 'Theather Hall Manager'),"+
				"(1474, 'Theory Teacher'),"+
				"(1475, 'Three wheel Driver'),"+
				"(1476, 'Ticket Clerk'),"+
				"(1477, 'Ticket Collector'),"+
				"(1478, 'Time Keeper'),"+
				"(1479, 'Timekeeper Clerk'),"+
				"(1480, 'Tinker'),"+
				"(1481, 'Tinker Metal Worker'),"+
				"(1482, 'Tinkers'),"+
				"(1483, 'Tinsmith'),"+
				"(1484, 'Tourist Services Officer'),"+
				"(1485, 'Tracer'),"+
				"(1486, 'Tractor Driver'),"+
				"(1487, 'Tractor Operator'),"+
				"(1488, 'Trade Asst.'),"+
				"(1489, 'Traditional Art Asst.'),"+
				"(1490, 'Traffic Costing Officer'),"+
				"(1491, 'Training & Education Officer'),"+
				"(1492, 'Training and Research Asst.'),"+
				"(1493, 'Training Asst.'),"+
				"(1494, 'Training Manager'),"+
				"(1495, 'Training Officer'),"+
				"(1496, 'Trainning Officer'),"+
				"(1497, 'Tranport Officer'),"+
				"(1498, 'Translator'),"+
				"(1499, 'Translator Asst.'),"+
				"(1500, 'Translator Clerk'),"+
				"(1501, 'Translator cum Receptionist'),"+
				"(1502, 'Translator Superintendent'),"+
				"(1503, 'Translator/Interpretor'),"+
				"(1504, 'Translator/Typist'),"+
				"(1505, 'Transport Forman'),"+
				"(1506, 'Transport Officer'),"+
				"(1507, 'Transport Overseer'),"+
				"(1508, 'Travelling Ticket Examiner'),"+
				"(1509, 'Travelling Ticket Inspector'),"+
				"(1510, 'Tree Cutter'),"+
				"(1511, 'Tree Maintainer'),"+
				"(1512, 'Tumer'),"+
				"(1513, 'Turkey'),"+
				"(1514, 'Tutor Audiology Technicians School'),"+
				"(1515, 'Tutor ECG School'),"+
				"(1516, 'Tutor MLT'),"+
				"(1517, 'Tutor Nursing'),"+
				"(1518, 'Tutor Occupational Therapy'),"+
				"(1519, 'Tutor Ophthalmic Technologist'),"+
				"(1520, 'Tutor Pharmacist'),"+
				"(1521, 'Tutor PHI'),"+
				"(1522, 'Tutor PHLT School'),"+
				"(1523, 'Tutor Physiotherapy'),"+
				"(1524, 'Tutor Prosthetists & Orthotists'),"+
				"(1525, 'Tutor Public Health Nursing Sister'),"+
				"(1526, 'Tutor Radiography School'),"+
				"(1527, 'Tutor School Dental Therapist School'),"+
				"(1528, 'TV /Video Cameraman'),"+
				"(1529, 'Type Setter'),"+
				"(1530, 'Typewriter Mechanic'),"+
				"(1531, 'Typist'),"+
				"(1532, 'Uni.Asst'),"+
				"(1533, 'Unskilled Labourer'),"+
				"(1534, 'Utility Receiving Asst.'),"+
				"(1535, 'Vaccinator'),"+
				"(1536, 'Vehical Asst.'),"+
				"(1537, 'Vehicle Mechanic'),"+
				"(1538, 'Very Typer / Headliner'),"+
				"(1539, 'Vet. Investigation Officer'),"+
				"(1540, 'Vet. Investigation Specialist'),"+
				"(1541, 'Veterinary Surgeon'),"+
				"(1542, 'Vetinary Economic Specialist'),"+
				"(1543, 'Vibrating Roller Operator'),"+
				"(1544, 'Vice President'),"+
				"(1545, 'Video Camera Aide'),"+
				"(1546, 'Video Camera Asst.'),"+
				"(1547, 'Video Camera Operator'),"+
				"(1548, 'Video Camera Operator Aide'),"+
				"(1549, 'Video Camera Technician'),"+
				"(1550, 'Video Cameraman'),"+
				"(1551, 'Video cameramen'),"+
				"(1552, 'Video Editing Aide'),"+
				"(1553, 'Video Editor'),"+
				"(1554, 'Video Lighting/ Electric. Aide'),"+
				"(1555, 'Video/ Photography Editing Officer'),"+
				"(1556, 'Videographer'),"+
				"(1557, 'Vocational Instructor'),"+
				"(1558, 'Volcanizer'),"+
				"(1559, 'Wakf Tribunal Secretary'),"+
				"(1560, 'Ward Clerk'),"+
				"(1561, 'Ward Sister'),"+
				"(1562, 'Warden'),"+
				"(1563, 'Warden Attendent'),"+
				"(1564, 'Warehouseman'),"+
				"(1565, 'Watch Repairer'),"+
				"(1566, 'Watcher'),"+
				"(1567, 'Watchman'),"+
				"(1568, 'Water Pipe Technician'),"+
				"(1569, 'Water Pump Operator'),"+
				"(1570, 'Web Editor'),"+
				"(1571, 'Web/Network Administrator'),"+
				"(1572, 'Welder'),"+
				"(1573, 'Welfare Asst.'),"+
				"(1574, 'Welfare Officer'),"+
				"(1575, 'Wharf Administrator'),"+
				"(1576, 'Wharf Clerk'),"+
				"(1577, 'Wharf Field Officer'),"+
				"(1578, 'Wild Life Ranger'),"+
				"(1579, 'Wildlife Conservating Officer'),"+
				"(1580, 'Wildlife Guard'),"+
				"(1581, 'Wireman'),"+
				"(1582, 'Woman Rest House Servant'),"+
				"(1583, 'Women Asst. Superintendent of Police'),"+
				"(1584, 'Women Chief Inspector of Police'),"+
				"(1585, 'Women Devp. Officer'),"+
				"(1586, 'Women Inspector of Police'),"+
				"(1587, 'Women Police Constable'),"+
				"(1588, 'Women Senior Superintendent of Police'),"+
				"(1589, 'Women Sub Inspector of Police'),"+
				"(1590, 'Women Superintendent of Police'),"+
				"(1591, 'Wood Cutter Labourer'),"+
				"(1592, 'Work Oversear'),"+
				"(1593, 'Work Supervisor'),"+
				"(1594, 'Works Manager'),"+
				"(1595, 'Workshop Asst.'),"+
				"(1596, 'Writer'),"+
				"(1597, 'X-Ray Inspector'),"+
				"(1598, 'Yard Foremen'),"+
				"(1599, 'Yard Master'),"+
				"(1600, 'Youth Devp. Asst.'),"+
				"(1601, 'Zoology Asst.'),"+
				"(1602, 'Development Officer'),"+
				"(1603, 'Police Constable'),"+
				"(1604, 'Women Police Constable')";

		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ps.executeUpdate();
			ps = dbConn.prepareStatement(insertSql);
			ps.executeUpdate();
			ps = dbConn.prepareStatement(insertDesignation);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
	}
	
	/**
	 * @set bank
	 * @param dto
	 */
	private void insertBank(BankDTO dto) {
		Connection dbConn = null;
		String sql = "INSERT INTO bank VALUES(?,?)"; 

		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
		
	}
	
	/**
	 * @set bank
	 * @param dto
	 */
	private void insertBranch(BranchDTO dto, int bankId) {
		Connection dbConn = null;
		String sql = "INSERT INTO branch VALUES(?,?,?)"; 

		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setInt(3, bankId);
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
		
	}
	
	private void truncateBankBranch() {
		Connection dbConn = null;
		String sqlBank = "DELETE FROM bank";
		String sqlBranch = "DELETE FROM branch";

		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sqlBranch);
			
			ps.execute();
			
			ps = dbConn.prepareStatement(sqlBank);
			
			ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
	}
	
	/**
	 * @get bank count
	 * @return
	 */
	private int getBankCount() {
		int bankCount = 0;
		Connection dbConn = null;
		String sql = "SELECT COUNT(*) FROM bank";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bankCount = rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return bankCount;
	}
	
	/**
	 * @get branch count
	 * @return
	 */
	private int getBranchCount() {
		int branchCount = 0;
		Connection dbConn = null;
		String sql = "SELECT COUNT(*) FROM branch";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				branchCount = rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return branchCount;
	}
	
	/**
	 * @return
	 */
	public ArrayList<String> getBank() {
		ArrayList<String> bankList = new ArrayList<String>();
		Connection dbConn = null;
		String sql = "SELECT * FROM bank";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				bankList.add(rs.getString("NAME"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return bankList;
	}
	
	public String getBankFromId(int Id) {
		String bank = null;
		Connection dbConn = null;
		String sql = "SELECT b.NAME FROM branch br INNER JOIN BANK b ON br.bankId = b.ID WHERE br.id = ?";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bank = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return bank;
	}
	
	public String getBranchFromId(int Id) {
		String branch = null;
		Connection dbConn = null;
		String sql = "SELECT br.branchName FROM branch br WHERE br.id = ?";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				branch = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return branch;
	}
	
	public int getBranchIdFromName(String branchname,String bankName) {
		int branch = 0;
		Connection dbConn = null;
		String sql = "SELECT br.id FROM branch br WHERE br.branchName = ? and br.bankId = (select id from bank where name = ? )";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, branchname);
			ps.setString(2, bankName);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				branch = rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return branch;
	}
	
	/**
	 * @get branch list according to the bank name
	 * @param name
	 * @return
	 */
	public ArrayList<String> getBranchesAccordingBankName(String name) {
		
		ArrayList<String> branchList = new ArrayList<String>();
		Connection dbConn = null;
		String sql = "SELECT br.branchName FROM branch br where br.bankId = (SELECT b.ID FROM bank b where  b.NAME = ?)";
		
		try {
			
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				branchList.add(rs.getString("branchName"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return branchList;
		
	}
	
	/**
	 * @get office list
	 * @return
	 */
	public ArrayList<String> getOffice() {
		ArrayList<String> officeList = new ArrayList<String>();
		Connection dbConn = null;
		String sql = "SELECT * FROM Office";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				officeList.add(rs.getString("institution_name"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return officeList;
	}
	
	/**
	 * @get List of designation
	 * @return
	 */
	public ArrayList<String> getDesignation() {
		ArrayList<String> designationList = new ArrayList<String>();
		Connection dbConn = null;
		String sql = "SELECT * FROM designations";
		
		try {
			dbConn = dbConnManager.getConnection();
			PreparedStatement ps = dbConn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				designationList.add(rs.getString("designation"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbConnManager.closeConnection(dbConn);
		}
		
		return designationList;
	}
	
	public void updateSqlite() {
		BankDAO dao = new BankDAO();
		
		BankList list = dao.getUpdates(getBankCount(), getBranchCount());
		
		if(list != null) {
			truncateBankBranch();
			for(BankDTO bank : list.getList()) {
				insertBank(bank);
//				for(BranchDTO branch : bank.getBranches()) {
//					insertBranch(branch, bank.getId());
//				}
			}
		}
	}

}
