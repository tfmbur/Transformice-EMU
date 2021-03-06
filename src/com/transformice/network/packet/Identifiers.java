package com.transformice.network.packet;

public class Identifiers {
    public static class screen {
        public static class send {
            public static final int[] login = {26, 8};
            public static final int[] version = {26, 3};
            public static final int[] souris = {26, 33};
            public static final int[] result = {26, 12};
            public static final int[] identification = {26, 2};

            public static final int[] banner = {16, 9};
            public static final int[] image = {100, 99};
        }
    }

    public static class room {
        public static class send {
            public static final int[] time_stamp = {28, 2};
            public static final int[] enter = {5, 21};
            public static final int[] new_map = {5, 2};
            public static final int[] round_time = {5, 22};
            public static final int[] map_start_timer = {5, 10};
            public static final int[] remove_cheese = {8, 19};
        }
    }

    public static class old {
        public static class room {
            public static class send {
                public static final int[] sync = {8, 21};
                public static final int[] player_list = {8, 9};
                public static final int[] respawn = {8, 8};
                public static final int[] anchors = {5, 7};
            }
        }
    }

    public static final class tribulle {
        public static final class send {
            public static final int[] tokens = {60, 1};
            public static final int ET_ResultatIdentificationService = 2;
            public static final int ET_ResultatMiseAJourLocalisation = 5;
            public static final int ET_ResultatMiseAJourLocalisations = 7;
            public static final int ET_ResultatMessageCanal = 21;
            public static final int ET_SignaleMessageCanal = 22;
            public static final int ET_ResultatRejoindreCanal = 24;
            public static final int ET_ResultatQuitterCanal = 26;
            public static final int ET_SignaleRejointCanal = 27;
            public static final int ET_SignaleQuitteCanal = 28;
            public static final int ET_SignaleMembreRejointCanal = 29;
            public static final int ET_SignaleMembresRejoignentCanal = 30;
            public static final int ET_SignaleMembreQuitteCanal = 31;
            public static final int ET_SignaleMembresQuittentCanal = 32;
            public static final int ET_ResultatMessagePrive = 34;
            public static final int ET_RecoitMessagePriveSysteme = 35;
            public static final int ET_RecoitMessagePrive = 36;
            public static final int ET_ResultatDefinitModeSilence = 40;
            public static final int ET_ResultatDemandeMembresCanal = 42;
            public static final int ET_ErreurDemandeMembresCanal = 43;
            public static final int ET_ResultatAjoutAmi = 45;
            public static final int ET_ResultatRetireAmi = 47;
            public static final int ET_ResultatListeAmis = 49;
            public static final int ET_ErreurListeAmis = 50;
            public static final int ET_SignaleAjoutAmi = 51;
            public static final int ET_SignaleModificationLocalisationAmi = 52;
            public static final int ET_SignaleRetraitAmi = 53;
            public static final int ET_SignaleConnexionAmi = 54;
            public static final int ET_SignaleDeconnexionAmi = 55;
            public static final int ET_SignaleConnexionAmis = 56;
            public static final int ET_SignaleDeconnexionAmis = 57;
            public static final int ET_SignaleAjoutAmiBidirectionnel = 58;
            public static final int ET_SignaleRetraitAmiBidirectionnel = 59;
            public static final int ET_ResultatDemandeEnMariage = 61;
            public static final int ET_ErreurDemandeEnMariage = 62;
            public static final int ET_SignaleDemandeEnMariage = 63;
            public static final int ET_ResultatRepondDemandeEnMariage = 65;
            public static final int ET_SignaleMariage = 66;
            public static final int ET_ResultatDemandeDivorce = 68;
            public static final int ET_SignaleDivorce = 69;
            public static final int ET_ResultatAjoutListeNoire = 71;
            public static final int ET_ResultatRetireListeNoire = 73;
            public static final int ET_ResultatListeNoire = 75;
            public static final int ET_ErreurListeNoire = 76;
            public static final int ET_SignaleAjoutListeNoire = 77;
            public static final int ET_SignaleRetraitListeNoire = 78;
            public static final int ET_CreerTribu = 79;
            public static final int ET_ResultatCreerTribu = 80;
            public static final int ET_SignaleTribuCreee = 81;
            public static final int ET_SignaleInvitationTribu = 82;
            public static final int ET_ErreurRepondInvitationTribu = 84;
            public static final int ET_ResultatInformationsTribu = 86;
            public static final int ET_ErreurInformationsTribu = 87;
            public static final int ET_ResultatInformationsTribuSimple = 89;
            public static final int ET_ErreurInformationsTribuSimple = 90;
            public static final int ET_ResultatMembresTribu = 92;
            public static final int ET_ErreurMembresTribu = 93;
            public static final int ET_ResultatQuitterTribu = 95;
            public static final int ET_ResultatListeHistoriqueTribu = 97;
            public static final int ET_ErreurListeHistoriqueTribu = 98;
            public static final int ET_SignaleConnexionMembre = 99;
            public static final int ET_SignaleDeconnexionMembre = 100;
            public static final int ET_SignaleConnexionMembres = 101;
            public static final int ET_SignaleDeconnexionMembres = 102;
            public static final int ET_SignaleChangementMessageJour = 103;
            public static final int ET_SignaleChangementCodeMaisonTFM = 104;
            public static final int ET_SignaleChangementRang = 105;
            public static final int ET_SignaleExclusion = 106;
            public static final int ET_SignaleNouveauMembre = 107;
            public static final int ET_SignaleDepartMembre = 108;
            public static final int ET_SignaleModificationLocalisationMembreTribu = 109;
            public static final int ET_ResultatChangerMessageJour = 111;
            public static final int ET_ResultatExclureMembre = 115;
            public static final int ET_ResultatInviterMembre = 117;
            public static final int ET_ErreurInviterMembre = 118;
            public static final int ET_ResultatChangerCodeMaisonTFM = 120;
            public static final int ET_ResultatListeRangs = 122;
            public static final int ET_ErreurListeRangs = 123;
            public static final int ET_ResultatAffecterRang = 125;
            public static final int ET_ResultatAjouterRang = 127;
            public static final int ET_ErreurAjouterRang = 128;
            public static final int ET_ResultatSupprimerRang = 130;
            public static final int ET_ResultatRenommerRang = 132;
            public static final int ET_ResultatAjouterDroitRang = 134;
            public static final int ET_ResultatSupprimerDroitRang = 136;
            public static final int ET_ResultatInverserOrdreRangs = 138;
            public static final int ET_ResultatDesignerChefSpirituel = 142;
            public static final int ET_ResultatRenommerTribu = 144;
            public static final int ET_ResultatDissoudreTribu = 146;
            public static final int ET_SignaleDissolutionTribu = 147;
            public static final int ET_ResultatDonneesUtilisateur = 153;
            public static final int ET_ErreurDonneesUtilisateur = 154;
            public static final int ET_ResultatDefinitDonneesUtilisateur = 156;
            public static final int ET_ResultatChangerDeGenre = 158;
            public static final int ET_SignaleChangementAvatar = 160;
            public static final int ET_SignaleChangementDeGenre = 159;
            public static final int ET_DemandeNouveauxMessagesPrivesWeb = 161;
            public static final int ET_DemandeNouveauxMessagesPrivesWebEnMasse = 162;
            public static final int ET_SignalNouveauxMessagesPrivesWeb = 163;
            public static final int ET_SignalNouveauMessagePriveWeb = 164;
            public static final int ET_ReponseDemandeInfosJeuUtilisateur = 166;
            public static final int ET_ErreurDemandeInfosJeuUtilisateur = 167;
        }

        public static final class recv {
            public static final int ST_IdentificationService = 1;
            public static final int ST_PingUtilisateur = 3;
            public static final int ST_MiseAJourLocalisation = 4;
            public static final int ST_MiseAJourLocalisations = 6;
            public static final int ST_EnvoitMessageCanal = 20;
            public static final int ST_RejoindreCanal = 23;
            public static final int ST_QuitterCanal = 25;
            public static final int ST_EnvoitMessagePrive = 33;
            public static final int ST_DefinitModeSilence = 39;
            public static final int ST_DemandeMembresCanal = 41;
            public static final int ST_AjoutAmi = 44;
            public static final int ST_RetireAmi = 46;
            public static final int ST_ListeAmis = 48;
            public static final int ST_DemandeEnMariage = 60;
            public static final int ST_RepondDemandeEnMariage = 64;
            public static final int ST_DemandeDivorce = 67;
            public static final int ST_AjoutListeNoire = 70;
            public static final int ST_RetireListeNoire = 72;
            public static final int ST_ListeNoire = 74;
            public static final int ST_CreerTribu = 79;
            public static final int ST_ResultatCreerTribu = 80;
            public static final int ST_RepondInvitationTribu = 83;
            public static final int ST_DemandeInformationsTribu = 85;
            public static final int ST_DemandeInformationsTribuSimpleParNom = 88;
            public static final int ST_DemandeMembresTribu = 91;
            public static final int ST_QuitterTribu = 94;
            public static final int ST_ListeHistoriqueTribu = 96;
            public static final int ST_ChangerMessageJour = 110;
            public static final int ST_ExclureMembre = 114;
            public static final int ST_InviterMembre = 116;
            public static final int ST_ChangerCodeMaisonTFM = 119;
            public static final int ST_ListeRangs = 121;
            public static final int ST_AffecterRang = 124;
            public static final int ST_AjouterRang = 126;
            public static final int ST_SupprimerRang = 129;
            public static final int ST_RenommerRang = 131;
            public static final int ST_AjouterDroitRang = 133;
            public static final int ST_SupprimerDroitRang = 135;
            public static final int ST_InverserOrdreRangs = 137;
            public static final int ST_DesignerChefSpirituel = 141;
            public static final int ST_RenommerTribu = 143;
            public static final int ST_DissoudreTribu = 145;
            public static final int ST_DemandeDonneesUtilisateur = 152;
            public static final int ST_DefinitDonneesUtilisateur = 155;
            public static final int ST_ChangerDeGenre = 157;
            public static final int ST_SignaleChangementDeGenre = 159;
            public static final int ST_SignaleChangementAvatar = 160;
            public static final int ST_SignalNouveauxMessagesPrivesWeb = 163;
            public static final int ST_SignalNouveauMessagePriveWeb = 164;
            public static final int ST_RequeteDemandeInfosJeuUtilisateur = 165;
        }
    }
}
