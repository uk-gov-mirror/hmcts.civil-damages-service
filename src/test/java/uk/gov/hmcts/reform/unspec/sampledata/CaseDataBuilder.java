package uk.gov.hmcts.reform.unspec.sampledata;

import uk.gov.hmcts.reform.ccd.model.Organisation;
import uk.gov.hmcts.reform.ccd.model.OrganisationPolicy;
import uk.gov.hmcts.reform.unspec.enums.AllocatedTrack;
import uk.gov.hmcts.reform.unspec.enums.CaseState;
import uk.gov.hmcts.reform.unspec.enums.ClaimType;
import uk.gov.hmcts.reform.unspec.enums.PersonalInjuryType;
import uk.gov.hmcts.reform.unspec.enums.ReasonForProceedingOnPaper;
import uk.gov.hmcts.reform.unspec.enums.RespondentResponseType;
import uk.gov.hmcts.reform.unspec.enums.ResponseIntention;
import uk.gov.hmcts.reform.unspec.enums.YesOrNo;
import uk.gov.hmcts.reform.unspec.model.BusinessProcess;
import uk.gov.hmcts.reform.unspec.model.CaseData;
import uk.gov.hmcts.reform.unspec.model.ClaimProceedsInCaseman;
import uk.gov.hmcts.reform.unspec.model.ClaimValue;
import uk.gov.hmcts.reform.unspec.model.CloseClaim;
import uk.gov.hmcts.reform.unspec.model.CorrectEmail;
import uk.gov.hmcts.reform.unspec.model.CourtLocation;
import uk.gov.hmcts.reform.unspec.model.Fee;
import uk.gov.hmcts.reform.unspec.model.IdamUserDetails;
import uk.gov.hmcts.reform.unspec.model.Party;
import uk.gov.hmcts.reform.unspec.model.PaymentDetails;
import uk.gov.hmcts.reform.unspec.model.ResponseDocument;
import uk.gov.hmcts.reform.unspec.model.SolicitorOrganisationDetails;
import uk.gov.hmcts.reform.unspec.model.SolicitorReferences;
import uk.gov.hmcts.reform.unspec.model.StatementOfTruth;
import uk.gov.hmcts.reform.unspec.model.common.DynamicList;
import uk.gov.hmcts.reform.unspec.model.common.DynamicListElement;
import uk.gov.hmcts.reform.unspec.model.common.Element;
import uk.gov.hmcts.reform.unspec.model.documents.CaseDocument;
import uk.gov.hmcts.reform.unspec.model.dq.Applicant1DQ;
import uk.gov.hmcts.reform.unspec.model.dq.DisclosureOfElectronicDocuments;
import uk.gov.hmcts.reform.unspec.model.dq.DisclosureOfNonElectronicDocuments;
import uk.gov.hmcts.reform.unspec.model.dq.Experts;
import uk.gov.hmcts.reform.unspec.model.dq.FileDirectionsQuestionnaire;
import uk.gov.hmcts.reform.unspec.model.dq.FurtherInformation;
import uk.gov.hmcts.reform.unspec.model.dq.Hearing;
import uk.gov.hmcts.reform.unspec.model.dq.HearingSupport;
import uk.gov.hmcts.reform.unspec.model.dq.RequestedCourt;
import uk.gov.hmcts.reform.unspec.model.dq.Respondent1DQ;
import uk.gov.hmcts.reform.unspec.model.dq.WelshLanguageRequirements;
import uk.gov.hmcts.reform.unspec.model.dq.Witnesses;
import uk.gov.hmcts.reform.unspec.service.flowstate.FlowState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static java.time.LocalDate.now;
import static uk.gov.hmcts.reform.unspec.enums.AllocatedTrack.FAST_CLAIM;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.AWAITING_APPLICANT_INTENTION;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.AWAITING_CASE_DETAILS_NOTIFICATION;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.AWAITING_RESPONDENT_ACKNOWLEDGEMENT;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.CASE_DISMISSED;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.CASE_ISSUED;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.PENDING_CASE_ISSUED;
import static uk.gov.hmcts.reform.unspec.enums.CaseState.PROCEEDS_IN_HERITAGE_SYSTEM;
import static uk.gov.hmcts.reform.unspec.enums.PaymentStatus.FAILED;
import static uk.gov.hmcts.reform.unspec.enums.PaymentStatus.SUCCESS;
import static uk.gov.hmcts.reform.unspec.enums.PersonalInjuryType.ROAD_ACCIDENT;
import static uk.gov.hmcts.reform.unspec.enums.ResponseIntention.FULL_DEFENCE;
import static uk.gov.hmcts.reform.unspec.enums.YesOrNo.NO;
import static uk.gov.hmcts.reform.unspec.enums.YesOrNo.YES;
import static uk.gov.hmcts.reform.unspec.enums.dq.HearingLength.ONE_DAY;

public class CaseDataBuilder {

    public static final String LEGACY_CASE_REFERENCE = "000LR001";
    public static final Long CASE_ID = 1594901956117591L;
    public static final LocalDateTime RESPONSE_DEADLINE = now().plusDays(14).atTime(23, 59, 59);
    public static final LocalDateTime APPLICANT_RESPONSE_DEADLINE = LocalDateTime.now().plusDays(120);
    public static final LocalDate CLAIM_ISSUED_DATE = now();
    public static final LocalDateTime DEADLINE = LocalDate.of(2020, 1, 1).atStartOfDay();

    // Create Claim
    private Long ccdCaseReference;
    private SolicitorReferences solicitorReferences;
    private CourtLocation courtLocation;
    private Party applicant1;
    private YesOrNo applicant1LitigationFriendRequired;
    private Party respondent1;
    private YesOrNo respondent1Represented;
    private String respondentSolicitor1EmailAddress;
    private ClaimValue claimValue;
    private ClaimType claimType;
    private String claimTypeOther;
    private PersonalInjuryType personalInjuryType;
    private String personalInjuryTypeOther;
    private DynamicList applicantSolicitor1PbaAccounts;
    private Fee claimFee;
    private StatementOfTruth applicantSolicitor1ClaimStatementOfTruth;
    private String paymentReference;
    private String legacyCaseReference;
    private AllocatedTrack allocatedTrack;
    private CaseState ccdState;
    private List<Element<CaseDocument>> systemGeneratedCaseDocuments;
    private PaymentDetails paymentDetails;
    private CorrectEmail applicantSolicitor1CheckEmail;
    private IdamUserDetails applicantSolicitor1UserDetails;
    //Deadline extension
    private LocalDate respondentSolicitor1AgreedDeadlineExtension;
    //Acknowledge Claim
    private ResponseIntention respondent1ClaimResponseIntentionType;
    // Defendant Response
    private RespondentResponseType respondent1ClaimResponseType;
    private ResponseDocument respondent1ClaimResponseDocument;
    private Respondent1DQ respondent1DQ;
    private Applicant1DQ applicant1DQ;
    // Claimant Response
    private YesOrNo applicant1ProceedWithClaim;
    private ResponseDocument applicant1DefenceResponseDocument;
    private BusinessProcess businessProcess;

    //Case proceeds in caseman
    private ClaimProceedsInCaseman claimProceedsInCaseman;

    private CloseClaim withdrawClaim;
    private CloseClaim discontinueClaim;
    private YesOrNo respondent1OrgRegistered;
    private OrganisationPolicy applicant1OrganisationPolicy;
    private OrganisationPolicy respondent1OrganisationPolicy;

    //dates
    private LocalDateTime submittedDate;
    private LocalDateTime paymentSuccessfulDate;
    private LocalDate issueDate;
    private LocalDateTime claimNotificationDeadline;
    private LocalDateTime claimNotificationDate;
    private LocalDateTime claimDetailsNotificationDeadline;
    private LocalDateTime claimDetailsNotificationDate;
    private LocalDateTime respondent1ResponseDeadline;
    private LocalDateTime claimDismissedDeadline;
    private LocalDateTime respondent1TimeExtensionDate;
    private LocalDateTime respondent1AcknowledgeNotificationDate;
    private LocalDateTime respondent1ResponseDate;
    private LocalDateTime applicant1ResponseDeadline;
    private LocalDateTime applicant1ResponseDate;
    private LocalDateTime takenOfflineDate;
    private LocalDateTime claimDismissedDate;

    private SolicitorOrganisationDetails respondentSolicitor1OrganisationDetails;

    public CaseDataBuilder respondent1ResponseDeadline(LocalDateTime deadline) {
        this.respondent1ResponseDeadline = deadline;
        return this;
    }

    public CaseDataBuilder respondent1AcknowledgeNotificationDate(LocalDateTime dateTime) {
        this.respondent1AcknowledgeNotificationDate = dateTime;
        return this;
    }

    public CaseDataBuilder respondentSolicitor1AgreedDeadlineExtension(LocalDate extensionDate) {
        this.respondentSolicitor1AgreedDeadlineExtension = extensionDate;
        return this;
    }

    public CaseDataBuilder respondent1DQ() {
        respondent1DQ = Respondent1DQ.builder()
            .respondent1DQFileDirectionsQuestionnaire(FileDirectionsQuestionnaire.builder()
                                                          .explainedToClient(List.of("CONFIRM"))
                                                          .oneMonthStayRequested(YES)
                                                          .reactionProtocolCompliedWith(YES)
                                                          .build())
            .respondent1DQDisclosureOfElectronicDocuments(DisclosureOfElectronicDocuments.builder()
                                                              .reachedAgreement(YES)
                                                              .build())
            .respondent1DQDisclosureOfNonElectronicDocuments(DisclosureOfNonElectronicDocuments.builder()
                                                                 .directionsForDisclosureProposed(NO)
                                                                 .build())
            .respondent1DQExperts(Experts.builder().expertRequired(NO).build())
            .respondent1DQWitnesses(Witnesses.builder().witnessesToAppear(NO).build())
            .respondent1DQHearing(Hearing.builder().hearingLength(ONE_DAY).unavailableDatesRequired(NO).build())
            .respondent1DQRequestedCourt(RequestedCourt.builder().requestHearingAtSpecificCourt(NO).build())
            .respondent1DQHearingSupport(HearingSupport.builder().requirements(List.of()).build())
            .respondent1DQFurtherInformation(FurtherInformation.builder().futureApplications(NO).build())
            .respondent1DQLanguage(WelshLanguageRequirements.builder().build())
            .respondent1DQStatementOfTruth(StatementOfTruth.builder().name("John Doe").role("Solicitor").build())
            .build();
        return this;
    }

    public CaseDataBuilder respondent1DQ(Respondent1DQ respondent1DQ) {
        this.respondent1DQ = respondent1DQ;
        return this;
    }

    public CaseDataBuilder applicant1DQ() {
        applicant1DQ = Applicant1DQ.builder()
            .applicant1DQFileDirectionsQuestionnaire(FileDirectionsQuestionnaire.builder()
                                                         .explainedToClient(List.of("OTHER"))
                                                         .oneMonthStayRequested(NO)
                                                         .reactionProtocolCompliedWith(YES)
                                                         .build())
            .applicant1DQDisclosureOfElectronicDocuments(DisclosureOfElectronicDocuments.builder()
                                                             .reachedAgreement(YES)
                                                             .build())
            .applicant1DQDisclosureOfNonElectronicDocuments(DisclosureOfNonElectronicDocuments.builder()
                                                                .directionsForDisclosureProposed(NO)
                                                                .build())
            .applicant1DQExperts(Experts.builder().expertRequired(NO).build())
            .applicant1DQWitnesses(Witnesses.builder().witnessesToAppear(NO).build())
            .applicant1DQHearing(Hearing.builder().hearingLength(ONE_DAY).unavailableDatesRequired(NO).build())
            .applicant1DQRequestedCourt(RequestedCourt.builder().requestHearingAtSpecificCourt(NO).build())
            .applicant1DQHearingSupport(HearingSupport.builder().requirements(List.of()).build())
            .applicant1DQFurtherInformation(FurtherInformation.builder().futureApplications(NO).build())
            .applicant1DQLanguage(WelshLanguageRequirements.builder().build())
            .applicant1DQStatementOfTruth(StatementOfTruth.builder().name("Bob Jones").role("Solicitor").build())
            .build();
        return this;
    }

    public CaseDataBuilder applicant1DQ(Applicant1DQ applicant1DQ) {
        this.applicant1DQ = applicant1DQ;
        return this;
    }

    public CaseDataBuilder respondentSolicitor1OrganisationDetails(SolicitorOrganisationDetails organisationDetails) {
        this.respondentSolicitor1OrganisationDetails = organisationDetails;
        return this;
    }

    public CaseDataBuilder applicant1ProceedWithClaim(YesOrNo yesOrNo) {
        this.applicant1ProceedWithClaim = yesOrNo;
        return this;
    }

    public CaseDataBuilder respondent1ClaimResponseIntentionType(ResponseIntention responseIntention) {
        this.respondent1ClaimResponseIntentionType = responseIntention;
        return this;
    }

    public CaseDataBuilder claimValue(ClaimValue claimValue) {
        this.claimValue = claimValue;
        return this;
    }

    public CaseDataBuilder issueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public CaseDataBuilder systemGeneratedCaseDocuments(List<Element<CaseDocument>> systemGeneratedCaseDocuments) {
        this.systemGeneratedCaseDocuments = systemGeneratedCaseDocuments;
        return this;
    }

    public CaseDataBuilder applicant1(Party party) {
        this.applicant1 = party;
        return this;
    }

    public CaseDataBuilder respondent1(Party party) {
        this.respondent1 = party;
        return this;
    }

    public CaseDataBuilder legacyCaseReference(String legacyCaseReference) {
        this.legacyCaseReference = legacyCaseReference;
        return this;
    }

    public CaseDataBuilder respondent1Represented(YesOrNo isRepresented) {
        this.respondent1Represented = isRepresented;
        return this;
    }

    public CaseDataBuilder respondent1OrgRegistered(YesOrNo respondent1OrgRegistered) {
        this.respondent1OrgRegistered = respondent1OrgRegistered;
        return this;
    }

    public CaseDataBuilder claimProceedsInCaseman(ClaimProceedsInCaseman claimProceedsInCaseman) {
        this.claimProceedsInCaseman = claimProceedsInCaseman;
        return this;
    }

    public CaseDataBuilder applicant1OrganisationPolicy(OrganisationPolicy applicant1OrganisationPolicy) {
        this.applicant1OrganisationPolicy = applicant1OrganisationPolicy;
        return this;
    }

    public CaseDataBuilder respondent1OrganisationPolicy(OrganisationPolicy respondent1OrganisationPolicy) {
        this.respondent1OrganisationPolicy = respondent1OrganisationPolicy;
        return this;
    }

    public CaseDataBuilder caseReference(Long ccdCaseReference) {
        this.ccdCaseReference = ccdCaseReference;
        return this;
    }

    public CaseDataBuilder claimNotificationDeadline(LocalDateTime deadline) {
        this.claimNotificationDeadline = deadline;
        return this;
    }

    public CaseDataBuilder claimDismissedDate(LocalDateTime date) {
        this.claimDismissedDate = date;
        return this;
    }

    public CaseDataBuilder extensionDate(LocalDate extensionDate) {
        this.respondentSolicitor1AgreedDeadlineExtension = extensionDate;
        return this;
    }

    public CaseDataBuilder atState(FlowState.Main flowState) {
        switch (flowState) {
            case DRAFT:
                return atStateClaimDraft();
            case PENDING_CASE_ISSUED:
                return atStatePendingCaseIssued();
            case PAYMENT_SUCCESSFUL:
                return atStatePaymentSuccessful();
            case PAYMENT_FAILED:
                return atStatePaymentFailed();
            case AWAITING_CASE_NOTIFICATION:
                return atStateAwaitingCaseNotification();
            case AWAITING_CASE_DETAILS_NOTIFICATION:
                return atStateAwaitingCaseDetailsNotification();
            case CLAIM_ISSUED:
                return atStateClaimCreated();
            case EXTENSION_REQUESTED:
                return atStateExtensionRequested();
            case CLAIM_ACKNOWLEDGED:
                return atStateClaimAcknowledge();
            case RESPONDENT_FULL_DEFENCE:
                return atStateRespondentFullDefence();
            case RESPONDENT_FULL_ADMISSION:
                return atStateRespondentFullAdmission();
            case RESPONDENT_PART_ADMISSION:
                return atStateRespondentPartAdmission();
            case RESPONDENT_COUNTER_CLAIM:
                return atStateRespondentCounterClaim();
            case FULL_DEFENCE_PROCEED:
                return atStateApplicantRespondToDefenceAndProceed();
            case FULL_DEFENCE_NOT_PROCEED:
                return atStateApplicantRespondToDefenceAndNotProceed();
            case CLAIM_WITHDRAWN:
                return atStateClaimWithdrawn();
            case CLAIM_DISCONTINUED:
                return atStateClaimDiscontinued();
            case PROCEEDS_OFFLINE_ADMIT_OR_COUNTER_CLAIM:
                return atStateProceedsOfflineAdmissionOrCounterClaim();
            case PROCEEDS_OFFLINE_UNREPRESENTED_DEFENDANT:
                return atStateProceedsOfflineUnrepresentedDefendant();
            case PENDING_CLAIM_ISSUED_UNREGISTERED_DEFENDANT:
                return atStateProceedsOfflineUnregisteredDefendant();
            case CASE_PROCEEDS_IN_CASEMAN:
                return atStateCaseProceedsInCaseman();
            case CLAIM_DISMISSED_DEFENDANT_OUT_OF_TIME:
                return atStateClaimDismissed();
            case CLAIM_DISMISSED_PAST_CLAIM_DETAILS_NOTIFICATION_DEADLINE:
                return atStateClaimDismissedPastClaimDetailsNotificationDeadline();
            case TAKEN_OFFLINE_PAST_APPLICANT_RESPONSE_DEADLINE:
                return atStateTakenOfflinePastApplicantResponseDeadline();
            case CLAIM_DISMISSED_PAST_CLAIM_NOTIFICATION_DEADLINE:
                return atStateClaimDismissedPastClaimNotificationDeadline();
            default:
                throw new IllegalArgumentException("Invalid internal state: " + flowState);
        }
    }

    public CaseDataBuilder atStateClaimDismissedPastClaimNotificationDeadline() {
        atStateAwaitingCaseNotification();
        ccdState = CASE_DISMISSED;
        claimNotificationDeadline = LocalDateTime.now().minusDays(1);
        claimDismissedDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateClaimDismissedPastClaimDetailsNotificationDeadline() {
        atStateAwaitingCaseDetailsNotification();
        claimDetailsNotificationDeadline = LocalDateTime.now().minusDays(5);
        ccdState = CASE_DISMISSED;
        claimDismissedDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateProceedsOfflineUnrepresentedDefendant() {
        atStatePaymentSuccessful();
        ccdState = PROCEEDS_IN_HERITAGE_SYSTEM;
        issueDate = CLAIM_ISSUED_DATE;
        respondent1Represented = NO;
        takenOfflineDate = LocalDateTime.now();
        respondent1OrganisationPolicy = null;

        respondentSolicitor1OrganisationDetails = SolicitorOrganisationDetails.builder()
            .email("testorg@email.com")
            .organisationName("test org name")
            .fax("123123123")
            .dx("test org dx")
            .phoneNumber("0123456789")
            .address(AddressBuilder.defaults().build())
            .build();
        return this;
    }

    public CaseDataBuilder atStateProceedsOfflineUnrepresentedDefendantWithMinimalData() {
        atStatePaymentSuccessfulWithMinimalData();

        ccdState = PROCEEDS_IN_HERITAGE_SYSTEM;
        issueDate = CLAIM_ISSUED_DATE;
        respondent1Represented = NO;
        takenOfflineDate = LocalDateTime.now();
        respondent1OrganisationPolicy = null;

        respondentSolicitor1OrganisationDetails = SolicitorOrganisationDetails.builder()
            .email("testorg@email.com")
            .organisationName("test org name")
            .fax("123123123")
            .dx("test org dx")
            .phoneNumber("0123456789")
            .address(AddressBuilder.defaults().build())
            .build();
        return this;
    }

    public CaseDataBuilder atStateClaimDraftWithMinimalData() {
        courtLocation = CourtLocation.builder()
            .applicantPreferredCourt("121")
            .build();
        applicant1 = PartyBuilder.builder().companyWithMinimalData().build();
        applicant1LitigationFriendRequired = NO;
        applicantSolicitor1CheckEmail = CorrectEmail.builder()
            .email("civilunspecified@gmail.com")
            .correct(YES)
            .build();
        applicant1OrganisationPolicy = OrganisationPolicy.builder()
            .organisation(Organisation.builder().organisationID("QWERTY").build())
            .build();
        respondent1OrganisationPolicy = OrganisationPolicy.builder()
            .organisation(Organisation.builder().organisationID("QWERTY").build())
            .build();
        respondent1 = PartyBuilder.builder().companyWithMinimalData().build();
        respondent1Represented = NO;
        claimType = ClaimType.CLINICAL_NEGLIGENCE;
        claimValue = ClaimValue.builder()
            .statementOfValueInPennies(BigDecimal.valueOf(10000000))
            .build();
        claimFee = Fee.builder()
            .calculatedAmountInPence(TEN)
            .code("fee code")
            .version("version 1")
            .build();
        paymentReference = "some reference";
        respondentSolicitor1EmailAddress = "civilunspecified@gmail.com";
        applicantSolicitor1ClaimStatementOfTruth = StatementOfTruthBuilder.minimal().build();
        submittedDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStatePendingCaseIssuedWithMinimalData() {
        atStateClaimDraftWithMinimalData();
        legacyCaseReference = LEGACY_CASE_REFERENCE;
        allocatedTrack = FAST_CLAIM;
        ccdState = PENDING_CASE_ISSUED;
        ccdCaseReference = CASE_ID;
        return this;
    }

    public CaseDataBuilder atStatePaymentSuccessfulWithMinimalData() {
        atStatePendingCaseIssuedWithMinimalData();
        paymentDetails = PaymentDetails.builder()
            .status(SUCCESS)
            .reference("RC-1604-0739-2145-4711")
            .build();
        paymentSuccessfulDate = LocalDateTime.now();
        claimDetailsNotificationDeadline = LocalDateTime.now().plusDays(1);
        return this;
    }

    public CaseDataBuilder atStateAwaitingCaseNotificationWithMinimalData() {
        atStatePaymentSuccessfulWithMinimalData();
        ccdState = CASE_ISSUED;
        issueDate = CLAIM_ISSUED_DATE;
        claimNotificationDeadline = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateAwaitingCaseDetailsNotificationWithMinimalData() {
        atStateAwaitingCaseNotificationWithMinimalData();
        claimNotificationDate = LocalDateTime.now();
        claimDetailsNotificationDeadline = DEADLINE;
        ccdState = AWAITING_CASE_DETAILS_NOTIFICATION;
        return this;
    }

    public CaseDataBuilder atStateClaimCreatedWithMinimalData() {
        atStateAwaitingCaseDetailsNotificationWithMinimalData();

        claimDetailsNotificationDate = LocalDateTime.now();
        claimDismissedDeadline = LocalDateTime.now().plusMonths(6);
        ccdState = AWAITING_RESPONDENT_ACKNOWLEDGEMENT;
        respondent1ResponseDeadline = RESPONSE_DEADLINE;
        return this;
    }

    public CaseDataBuilder atStateRespondentRespondToClaimWithMinimalData(
        RespondentResponseType respondentResponseType
    ) {
        atStateServiceAcknowledgeWithMinimalData();
        respondent1ClaimResponseType = respondentResponseType;
        applicant1ResponseDeadline = APPLICANT_RESPONSE_DEADLINE;
        respondent1ResponseDate = LocalDateTime.now();
        ccdState = AWAITING_APPLICANT_INTENTION;
        return this;
    }

    public CaseDataBuilder atStateServiceAcknowledgeWithMinimalData() {
        atStateClaimCreatedWithMinimalData();
        respondent1ClaimResponseIntentionType = FULL_DEFENCE;
        return this;
    }

    public CaseDataBuilder atStateProceedsOfflineUnregisteredDefendant() {
        atStatePaymentSuccessful();
        ccdState = PROCEEDS_IN_HERITAGE_SYSTEM;
        issueDate = CLAIM_ISSUED_DATE;
        respondent1Represented = YES;
        respondent1OrgRegistered = NO;
        return this;
    }

    public CaseDataBuilder atStateClaimDiscontinued() {
        atStateClaimCreated();
        return discontinueClaim();
    }

    public CaseDataBuilder discontinueClaim() {
        this.ccdState = CASE_DISMISSED;
        this.discontinueClaim = CloseClaim.builder()
            .date(LocalDate.now())
            .reason("My reason")
            .build();
        return this;
    }

    public CaseDataBuilder discontinueClaim(CloseClaim closeClaim) {
        this.discontinueClaim = closeClaim;
        return this;
    }

    public CaseDataBuilder discontinueClaimFrom(FlowState.Main flowState) {
        atState(flowState);
        this.ccdState = CASE_DISMISSED;
        this.discontinueClaim = CloseClaim.builder()
            .date(LocalDate.now())
            .reason("My reason")
            .build();
        return this;
    }

    public CaseDataBuilder atStateClaimWithdrawn() {
        atStateClaimCreated();
        return withdrawClaim();
    }

    public CaseDataBuilder withdrawClaim(CloseClaim closeClaim) {
        this.withdrawClaim = closeClaim;
        return this;
    }

    public CaseDataBuilder withdrawClaim() {
        this.ccdState = CASE_DISMISSED;
        this.withdrawClaim = CloseClaim.builder()
            .date(LocalDate.now())
            .reason("My reason")
            .build();
        return this;
    }

    public CaseDataBuilder withdrawClaimFrom(FlowState.Main flowState) {
        atState(flowState);
        this.ccdState = CASE_DISMISSED;
        this.withdrawClaim = CloseClaim.builder()
            .date(LocalDate.now())
            .reason("My reason")
            .build();
        return this;
    }

    public CaseDataBuilder atStateClaimDraft() {
        solicitorReferences = SolicitorReferences.builder()
            .applicantSolicitor1Reference("12345")
            .respondentSolicitor1Reference("6789")
            .build();
        courtLocation = CourtLocation.builder()
            .applicantPreferredCourt("121")
            .build();
        claimValue = ClaimValue.builder()
            .statementOfValueInPennies(BigDecimal.valueOf(10000000))
            .build();
        claimType = ClaimType.PERSONAL_INJURY;
        personalInjuryType = ROAD_ACCIDENT;
        applicantSolicitor1PbaAccounts = DynamicList.builder()
            .value(DynamicListElement.builder().label("PBA0077597").build())
            .build();
        claimFee = Fee.builder()
            .version("1")
            .code("CODE")
            .calculatedAmountInPence(BigDecimal.valueOf(100))
            .build();
        applicant1 = PartyBuilder.builder().individual().build();
        respondent1 = PartyBuilder.builder().soleTrader().build();
        respondent1Represented = YES;
        respondent1OrgRegistered = YES;
        applicant1OrganisationPolicy = OrganisationPolicy.builder()
            .organisation(Organisation.builder().organisationID("QWERTY").build())
            .build();
        respondent1OrganisationPolicy = OrganisationPolicy.builder()
            .organisation(Organisation.builder().organisationID("QWERTY").build())
            .build();
        respondentSolicitor1EmailAddress = "civilunspecified@gmail.com";
        applicantSolicitor1ClaimStatementOfTruth = StatementOfTruthBuilder.defaults().build();
        submittedDate = LocalDateTime.now();
        applicantSolicitor1CheckEmail = CorrectEmail.builder().email("civilunspecified@gmail.com").correct(YES).build();
        return this;
    }

    public CaseDataBuilder atStatePendingCaseIssued() {
        atStateClaimDraft();
        legacyCaseReference = LEGACY_CASE_REFERENCE;
        allocatedTrack = FAST_CLAIM;
        ccdState = PENDING_CASE_ISSUED;
        ccdCaseReference = CASE_ID;
        return this;
    }

    public CaseDataBuilder atStatePaymentFailed() {
        atStatePendingCaseIssued();

        paymentDetails = PaymentDetails.builder()
            .status(FAILED)
            .errorMessage("Your account is deleted")
            .errorCode("CA-E0004")
            .build();
        return this;
    }

    public CaseDataBuilder atStatePaymentSuccessful() {
        atStatePendingCaseIssued();
        paymentDetails = PaymentDetails.builder()
            .status(SUCCESS)
            .reference("RC-1604-0739-2145-4711")
            .build();
        paymentSuccessfulDate = LocalDateTime.now();
        claimDetailsNotificationDeadline = LocalDateTime.now().plusDays(1);
        return this;
    }

    public CaseDataBuilder atStateAwaitingCaseNotification() {
        atStatePaymentSuccessful();
        ccdState = CASE_ISSUED;
        issueDate = CLAIM_ISSUED_DATE;
        claimNotificationDeadline = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateAwaitingCaseDetailsNotification() {
        atStateAwaitingCaseNotification();
        claimNotificationDate = LocalDateTime.now();
        claimDetailsNotificationDeadline = DEADLINE;
        ccdState = AWAITING_CASE_DETAILS_NOTIFICATION;
        return this;
    }

    public CaseDataBuilder atStateClaimCreated() {
        atStateAwaitingCaseDetailsNotification();
        claimDetailsNotificationDate = LocalDateTime.now();
        claimDismissedDeadline = LocalDateTime.now().plusMonths(6);
        ccdState = AWAITING_RESPONDENT_ACKNOWLEDGEMENT;
        respondent1ResponseDeadline = RESPONSE_DEADLINE;
        return this;
    }

    public CaseDataBuilder atStateExtensionRequested() {
        atStateClaimAcknowledge();
        respondentSolicitor1AgreedDeadlineExtension = LocalDate.now();
        respondent1TimeExtensionDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateCaseProceedsInCaseman() {
        atStateAwaitingCaseNotification();
        claimProceedsInCaseman = ClaimProceedsInCaseman.builder()
            .date(LocalDate.now())
            .reason(ReasonForProceedingOnPaper.APPLICATION)
            .build();
        takenOfflineDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateRespondentFullDefence() {
        atStateRespondentRespondToClaim(RespondentResponseType.FULL_DEFENCE);
        respondent1ClaimResponseDocument = ResponseDocument.builder()
            .file(DocumentBuilder.builder().documentName("defendant-response.pdf").build())
            .build();
        respondent1DQ();
        respondent1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateRespondentFullAdmission() {
        atStateRespondentRespondToClaim(RespondentResponseType.FULL_ADMISSION);
        takenOfflineDate = LocalDateTime.now();
        respondent1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateRespondentPartAdmission() {
        atStateRespondentRespondToClaim(RespondentResponseType.PART_ADMISSION);
        takenOfflineDate = LocalDateTime.now();
        respondent1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateRespondentCounterClaim() {
        atStateRespondentRespondToClaim(RespondentResponseType.COUNTER_CLAIM);
        takenOfflineDate = LocalDateTime.now();
        respondent1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateRespondentRespondToClaim(RespondentResponseType respondentResponseType) {
        atStateClaimAcknowledge();
        respondent1ClaimResponseType = respondentResponseType;
        applicant1ResponseDeadline = APPLICANT_RESPONSE_DEADLINE;
        respondent1ResponseDate = LocalDateTime.now();
        ccdState = AWAITING_APPLICANT_INTENTION;
        return this;
    }

    public CaseDataBuilder atStateProceedsOfflineAdmissionOrCounterClaim() {
        atStateRespondentFullDefence();
        ccdState = PROCEEDS_IN_HERITAGE_SYSTEM;
        return this;
    }

    public CaseDataBuilder atStateClaimDismissed() {
        atStateClaimCreated();
        ccdState = CASE_DISMISSED;
        claimDismissedDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateApplicantRespondToDefenceAndProceed() {
        atStateRespondentFullDefence();
        applicant1ProceedWithClaim = YES;
        applicant1DefenceResponseDocument = ResponseDocument.builder()
            .file(DocumentBuilder.builder().documentName("claimant-response.pdf").build())
            .build();
        applicant1DQ();
        applicant1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateApplicantRespondToDefenceAndNotProceed() {
        atStateRespondentFullDefence();
        applicant1ProceedWithClaim = NO;
        applicant1ResponseDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateClaimAcknowledge() {
        atStateClaimCreated();
        respondent1ClaimResponseIntentionType = FULL_DEFENCE;
        respondent1AcknowledgeNotificationDate = LocalDateTime.now();
        return this;
    }

    public CaseDataBuilder atStateTakenOfflinePastApplicantResponseDeadline() {
        atStateRespondentFullDefence();
        takenOfflineDate = LocalDateTime.now().plusDays(2);
        return this;
    }

    public CaseDataBuilder businessProcess(BusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
        return this;
    }

    public CaseDataBuilder applicantSolicitor1UserDetails(IdamUserDetails applicantSolicitor1UserDetails) {
        this.applicantSolicitor1UserDetails = applicantSolicitor1UserDetails;
        return this;
    }

    public static CaseDataBuilder builder() {
        return new CaseDataBuilder();
    }

    public CaseData build() {
        return CaseData.builder()
            // Create Claim
            .legacyCaseReference(legacyCaseReference)
            .allocatedTrack(allocatedTrack)
            .solicitorReferences(solicitorReferences)
            .courtLocation(courtLocation)
            .claimValue(claimValue)
            .claimType(claimType)
            .claimTypeOther(claimTypeOther)
            .personalInjuryType(personalInjuryType)
            .personalInjuryTypeOther(personalInjuryTypeOther)
            .applicantSolicitor1PbaAccounts(applicantSolicitor1PbaAccounts)
            .claimFee(claimFee)
            .applicant1(applicant1)
            .respondent1(respondent1)
            .respondent1Represented(respondent1Represented)
            .respondent1OrgRegistered(respondent1OrgRegistered)
            .respondentSolicitor1EmailAddress(respondentSolicitor1EmailAddress)
            .applicantSolicitor1ClaimStatementOfTruth(applicantSolicitor1ClaimStatementOfTruth)
            .paymentDetails(paymentDetails)
            .claimFee(claimFee)
            .paymentReference(paymentReference)
            .applicantSolicitor1CheckEmail(applicantSolicitor1CheckEmail)
            .applicantSolicitor1UserDetails(applicantSolicitor1UserDetails)
            //Deadline extension
            .respondentSolicitor1AgreedDeadlineExtension(respondentSolicitor1AgreedDeadlineExtension)
            // Acknowledge Claim
            .respondent1ClaimResponseIntentionType(respondent1ClaimResponseIntentionType)
            // Defendant Response
            .respondent1ClaimResponseType(respondent1ClaimResponseType)
            .respondent1ClaimResponseDocument(respondent1ClaimResponseDocument)
            // Claimant Response
            .applicant1ProceedWithClaim(applicant1ProceedWithClaim)
            .applicant1DefenceResponseDocument(applicant1DefenceResponseDocument)

            //Case procceds in Caseman
            .claimProceedsInCaseman(claimProceedsInCaseman)

            .ccdState(ccdState)
            .businessProcess(businessProcess)
            .ccdCaseReference(ccdCaseReference)
            .systemGeneratedCaseDocuments(systemGeneratedCaseDocuments)
            .withdrawClaim(withdrawClaim)
            .discontinueClaim(discontinueClaim)
            .respondent1DQ(respondent1DQ)
            .applicant1DQ(applicant1DQ)
            .respondentSolicitor1OrganisationDetails(respondentSolicitor1OrganisationDetails)
            .applicant1OrganisationPolicy(applicant1OrganisationPolicy)
            .respondent1OrganisationPolicy(respondent1OrganisationPolicy)

            //dates
            .submittedDate(submittedDate)
            .issueDate(issueDate)
            .claimNotificationDate(claimNotificationDate)
            .claimDetailsNotificationDate(claimDetailsNotificationDate)
            .paymentSuccessfulDate(paymentSuccessfulDate)
            .claimNotificationDeadline(claimNotificationDeadline)
            .claimDetailsNotificationDate(claimDetailsNotificationDate)
            .claimDetailsNotificationDeadline(claimDetailsNotificationDeadline)
            .respondent1ResponseDeadline(respondent1ResponseDeadline)
            .claimDismissedDeadline(claimDismissedDeadline)
            .respondent1TimeExtensionDate(respondent1TimeExtensionDate)
            .respondent1AcknowledgeNotificationDate(respondent1AcknowledgeNotificationDate)
            .respondent1ResponseDate(respondent1ResponseDate)
            .respondent1ResponseDeadline(respondent1ResponseDeadline)
            .applicant1ResponseDate(applicant1ResponseDate)
            .applicant1ResponseDeadline(applicant1ResponseDeadline)
            .takenOfflineDate(takenOfflineDate)
            .claimDismissedDate(claimDismissedDate)
            .build();
    }
}
