/**
 * 
 */
package com.libereco.server.auth.ebay;

import org.junit.Test;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.pictureservice.PictureInfo;
import com.ebay.sdk.pictureservice.PictureService;
import com.ebay.sdk.pictureservice.eps.eBayPictureServiceFactory;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BestOfferDetailsType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.FeeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingEnhancementsCodeType;
import com.ebay.soap.eBLBaseComponents.ListingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.ShippingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.UploadSiteHostedPicturesRequestType;
import com.ebay.soap.eBLBaseComponents.VATDetailsType;

/**
 * @author Aleksandar
 * 
 */
public class TestAddListing extends AbstractEbayTestCase {

	// TODO: Implement test cases, the current test methods are intended to
	// primarily explore interaction with the eBay API

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testDemoAddListingSandbox() throws Exception {
		ApiContext apiContext = getApiContextSandbox();
		AddItemCall api = new AddItemCall(apiContext);

		CurrencyCodeType currency = CurrencyCodeType.USD;

		// Let the call object to automatically generate UUID for my item.
		api.setAutoSetItemUUID(false);
		api.setSite(SiteCodeType.US);

		// Set detail level to retrieve item description.
		api.addDetailLevel(DetailLevelCodeType.ITEM_RETURN_DESCRIPTION);

		EbayListingItem ebayListingItem = new EbayListingItem();

		ebayListingItem.setItemTitle("RR Test item");
		ebayListingItem.setSubTitle("Subtitle");
		ebayListingItem.setDescription("Description");
		ebayListingItem.setListingDuration("Days_7");
		ebayListingItem.setQuantity(1);
		ebayListingItem.setCategoryId("139971");

		ebayListingItem.setStartPrice(1.00);

		// A Dutch auction, Buy It Now, Best Offer Only, Classified Ad, or
		// Fixed-Price item cannot have a reserve price.
		// ebayListingItem.setReservePrice(2.00);

		// ebayListingItem.setBuyItNowPrice(3.00);

		// ebayListingItem.setVatPercent(2.00f);
		// ebayListingItem.setPayPalEmail("test@example.com");

		// ebayListingItem.setBorderChecked(true);
		ebayListingItem.setBoldTitleChecked(true);

		// ShippingService is required if Insurance, SalesTax, or AutoPay is
		// specified.
		// ebayListingItem.setSetAutoPay(true);

		// ebayListingItem.setLotSize(10);

		// ebayListingItem.setBestOfferEnabled(true);

		ItemType item = buildItem(ebayListingItem);

		// fill in mandatory fields
		// handling time
		item.setDispatchTimeMax(Integer.valueOf(1));
		// return policy
		ReturnPolicyType returnPolicy = new ReturnPolicyType();
		returnPolicy.setReturnsAcceptedOption("ReturnsAccepted");
		item.setReturnPolicy(returnPolicy);

		item.setCurrency(currency);

		// TODO: Test
		// api.setPictureFiles(this.getPicturePathList());

		item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);

		// 1000 - New
		item.setConditionID(1000);

		api.setItem(item);
		FeesType fees = api.addItem();

		String itemId = item.getItemID();
		System.out.println("Item: " + itemId);
		FeeType ft = eBayUtil.findFeeByName(fees.getFee(), "ListingFee");
		System.out.println("Listing fee: " + ft.getFee().getValue());
	}

	@Test
	public void testDemoAddListingWithImageSandbox() throws Exception {

		ApiContext apiContext = getApiContextSandbox();
		AddItemCall api = new AddItemCall(apiContext);

		CurrencyCodeType currency = CurrencyCodeType.USD;

		// Let the call object to automatically generate UUID for my item.
		api.setAutoSetItemUUID(false);
		api.setSite(SiteCodeType.US);

		// Set detail level to retrieve item description.
		api.addDetailLevel(DetailLevelCodeType.ITEM_RETURN_DESCRIPTION);

		EbayListingItem ebayListingItem = new EbayListingItem();

		ebayListingItem.setItemTitle("Test item");
		ebayListingItem.setSubTitle("Subtitle");
		ebayListingItem.setDescription("Description");
		ebayListingItem.setListingDuration("Days_7");
		ebayListingItem.setQuantity(1);
		ebayListingItem.setCategoryId("139971");

		ebayListingItem.setStartPrice(1.00);

		// A Dutch auction, Buy It Now, Best Offer Only, Classified Ad, or
		// Fixed-Price item cannot have a reserve price.
		// ebayListingItem.setReservePrice(2.00);

		// ebayListingItem.setBuyItNowPrice(3.00);

		// ebayListingItem.setVatPercent(2.00f);

		// ebayListingItem.setPayPalEmail("test@example.com");

		// ebayListingItem.setBorderChecked(true);
		// ebayListingItem.setBoldTitleChecked(true);

		// ShippingService is required if Insurance, SalesTax, or AutoPay is
		// specified.
		// ebayListingItem.setSetAutoPay(true);

		// ebayListingItem.setLotSize(10);

		// ebayListingItem.setBestOfferEnabled(true);

		ItemType item = buildItem(ebayListingItem);

		// fill in mandatory fields
		// handling time
		item.setDispatchTimeMax(Integer.valueOf(1));
		// return policy
		ReturnPolicyType returnPolicy = new ReturnPolicyType();
		returnPolicy.setReturnsAcceptedOption("ReturnsAccepted");
		item.setReturnPolicy(returnPolicy);

		item.setCurrency(currency);

		// PictureService picService =
		// eBayPictureServiceFactory.getPictureService(apiContext);
		// api.setPictureService(picService);
		//
		// String[] picPaths = getPicturePathList();
		// if (picPaths != null) {
		// for (String pp : picPaths) {
		// uploadPicture(picService, apiContext, pp);
		// }
		//
		// api.setPictureFiles(picPaths);
		// }

		String[] picPaths = getPicturePathList();
		api.setPictureFiles(picPaths);

		item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);

		// 1000 - New
		item.setConditionID(1000);

		api.setItem(item);
		FeesType fees = api.addItem();

		String itemId = item.getItemID();
		System.out.println("Item: " + itemId);
		FeeType ft = eBayUtil.findFeeByName(fees.getFee(), "ListingFee");
		System.out.println("Listing fee: " + ft.getFee().getValue());
	}

	@Test
	public void testDemoUploadPicture() {
		ApiContext apiContext = getApiContextSandbox();

		PictureService picService = eBayPictureServiceFactory
				.getPictureService(apiContext);
		uploadPicture(picService, apiContext, "C:\\temp\\testThumbnail2.jpg");
	}

	@Test
	public void testDemoAddListingSandboxRequiredParamsOnly() throws Exception {
		ApiContext apiContext = getApiContextSandbox();
		AddItemCall api = new AddItemCall(apiContext);

		CurrencyCodeType currency = CurrencyCodeType.USD;

		// Let the call object to automatically generate UUID for my item.
		api.setAutoSetItemUUID(false);
		api.setSite(SiteCodeType.US);

		// Set detail level to retrieve item description.
		api.addDetailLevel(DetailLevelCodeType.ITEM_RETURN_DESCRIPTION);

		EbayListingItem ebayListingItem = new EbayListingItem();

		ebayListingItem.setItemTitle("Test item");
		//ebayListingItem.setSubTitle("Subtitle");
		ebayListingItem.setDescription("Description");
		ebayListingItem.setListingDuration("Days_7");
		ebayListingItem.setQuantity(1);
		ebayListingItem.setCategoryId("139971");

		ebayListingItem.setStartPrice(1.00);
		//ebayListingItem.setVatPercent(2.00f);

		ebayListingItem.setPayPalEmail("test@example.com");

		// ebayListingItem.setBoldTitleChecked(true);
		// ebayListingItem.setLotSize(10);

		ItemType item = buildItemRequiredParamsOnly(ebayListingItem);

		// fill in mandatory fields

		// handling time
		item.setDispatchTimeMax(Integer.valueOf(1));

		// return policy
		ReturnPolicyType returnPolicy = new ReturnPolicyType();
		returnPolicy.setReturnsAcceptedOption("ReturnsAccepted");
		item.setReturnPolicy(returnPolicy);

		item.setCurrency(currency);

		// TODO: Test
		// api.setPictureFiles(this.getPicturePathList());

		//item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);

		item.setConditionID(3000);

		api.setItem(item);
		FeesType fees = api.addItem();

		String itemId = item.getItemID();
		System.out.println("Item: " + itemId);

		FeeType ft = eBayUtil.findFeeByName(fees.getFee(), "ListingFee");
		System.out.println("Listing fee: " + ft.getFee());
	}

	// TODO: Add region information
	private ItemType buildItem(EbayListingItem ebayListingItem)
			throws Exception {

		String itemTitle = ebayListingItem.getItemTitle();
		String subTitle = ebayListingItem.getSubTitle();
		String description = ebayListingItem.getDescription();
		String listingDuration = ebayListingItem.getListingDuration();
		Integer quantity = ebayListingItem.getQuantity();
		String categoryId = ebayListingItem.getCategoryId();
		Double startPrice = ebayListingItem.getStartPrice();
		Double reservePrice = ebayListingItem.getReservePrice();
		Double buyItNowPrice = ebayListingItem.getBuyItNowPrice();
		Float vatPercent = ebayListingItem.getVatPercent();
		String payPalEmail = ebayListingItem.getPayPalEmail();
		boolean borderChecked = ebayListingItem.isBorderChecked();
		boolean boldTitleChecked = ebayListingItem.isBoldTitleChecked();
		boolean setAutoPay = ebayListingItem.isSetAutoPay();
		// Integer lotSize = ebayListingItem.getLotSize();
		Boolean bestOfferEnabled = ebayListingItem.getBestOfferEnabled();

		ItemType item = new ItemType();

		item.setTitle(itemTitle);

		if (subTitle != null && subTitle.length() > 0) {
			item.setSubTitle(subTitle);
		}

		item.setDescription(description);

		if (listingDuration != null) {
			item.setListingDuration(listingDuration);
		}

		item.setRegionID("0");
		item.setLocation("San Jose, CA");
		item.setCurrency(CurrencyCodeType.USD);

		item.setQuantity(quantity);
		item.setCountry(CountryCodeType.US);

		CategoryType cat = new CategoryType();
		cat.setCategoryID(categoryId);
		item.setPrimaryCategory(cat);

		AmountType at = new AmountType();
		at.setValue(startPrice);
		item.setStartPrice(at);

		if (reservePrice != null) {
			at = new AmountType();
			at.setValue(reservePrice);
			item.setReservePrice(at);
		}

		if (buyItNowPrice != null) {
			at = new AmountType();
			at.setValue(buyItNowPrice);
			item.setBuyItNowPrice(at);
		}

		// ServiceControlManager manager = ServiceControlManager.getInstance();
		// BuyerPaymentMethodCodeType[] arrPaymentMethods = manager
		// .processUserPaymentMethods(this.ckbPaymentServices,
		// this.ctrlPaymentServices);

		// BuyerPaymentMethodCodeType[] arrPaymentMethods = {
		// BuyerPaymentMethodCodeType.PAY_PAL };
		BuyerPaymentMethodCodeType[] arrPaymentMethods = { BuyerPaymentMethodCodeType.AM_EX };
		item.setPaymentMethods(arrPaymentMethods);

		//
		// ShippingDetailsType shippingDetails = manager
		// .processUserShippingDetails(this.shippingServiceOptions,
		// this.intlShippingServiceOptions);
		// if (shippingDetails != null) {
		// item.setShippingDetails(shippingDetails);
		// }
		//

		ShippingDetailsType shippingDetails = new ShippingDetailsType();
		shippingDetails.setShippingType(ShippingTypeCodeType.FLAT);
		
		ShippingServiceOptionsType shippingServiceOption = new ShippingServiceOptionsType();
		shippingServiceOption.setShippingService("USPSMedia");
		at = new AmountType();
		at.setValue(2.50);
		shippingServiceOption.setShippingServiceCost(at);

		ShippingServiceOptionsType[] shippingServiceOptions = { shippingServiceOption };
		shippingDetails.setShippingServiceOptions(shippingServiceOptions);
		item.setShippingDetails(shippingDetails);

		// ArrayList list = manager
		// .getSelectedCheckBoxList(this.ckbShipToLocations);
		// int size = list.size();
		// if (size > 0) {
		// String[] stl = new String[size];
		// for (int i = 0; i < size; i++) {
		// stl[i] = list.get(i).toString();
		// }
		// item.setShipToLocations(stl);
		// }

		if (payPalEmail != null && payPalEmail.length() > 0) {
			item.setPayPalEmailAddress(payPalEmail);
		}

		if (vatPercent != null) {
			VATDetailsType vatDetails = new VATDetailsType();
			vatDetails.setVATPercent(vatPercent);
			item.setVATDetails(vatDetails);
		}

		item.setAutoPay(setAutoPay);

		int cnt = 0;
		if (borderChecked) {
			cnt++;
		}

		if (boldTitleChecked) {
			cnt++;
		}

		if (cnt > 0) {
			ListingEnhancementsCodeType enhancements[] = new ListingEnhancementsCodeType[cnt];
			cnt = 0;
			if (borderChecked) {
				enhancements[cnt++] = ListingEnhancementsCodeType.BORDER;
			}
			if (boldTitleChecked) {
				enhancements[cnt] = ListingEnhancementsCodeType.BOLD_TITLE;
			}
			item.setListingEnhancement(enhancements);
		}

		// if (lotSize != null) {
		// item.setLotSize(lotSize);
		// }

		if (bestOfferEnabled != null) {
			BestOfferDetailsType bo = new BestOfferDetailsType();
			bo.setBestOfferEnabled(bestOfferEnabled);
			item.setBestOfferDetails(bo);
		}

		return item;
	}

	private void uploadPicture(PictureService picService,
			ApiContext apiContext, String filePath) {

		PictureInfo picInfo = new PictureInfo();
		picInfo.setPictureFilePath(filePath);
		UploadSiteHostedPicturesRequestType request = new UploadSiteHostedPicturesRequestType();

		// Enable watermark
		// request.setPictureWatermark(new PictureWatermarkCodeType[]
		// {PictureWatermarkCodeType.USER});

		// Extension in days
		// request.setExtensionInDays(days);

		// boolean success = picService.uploadPicture(PhotoDisplayCodeType.NONE,
		// picInfo, true);
		// System.out.println("Upload [" + filePath + "], success: " + success);

		boolean success = picService.UpLoadSiteHostedPicture(picInfo, request);
		System.out.println("Upload [" + filePath + "], success: " + success);
	}

	private ItemType buildItemRequiredParamsOnly(EbayListingItem ebayListingItem)
			throws Exception {

		String itemTitle = ebayListingItem.getItemTitle();
		// String subTitle = ebayListingItem.getSubTitle();
		String description = ebayListingItem.getDescription();
		String listingDuration = ebayListingItem.getListingDuration();
		Integer quantity = ebayListingItem.getQuantity();
		String categoryId = ebayListingItem.getCategoryId();
		Double startPrice = ebayListingItem.getStartPrice();
		Double reservePrice = ebayListingItem.getReservePrice();
		Double buyItNowPrice = ebayListingItem.getBuyItNowPrice();
		Float vatPercent = ebayListingItem.getVatPercent();
		String payPalEmail = ebayListingItem.getPayPalEmail();
		boolean borderChecked = ebayListingItem.isBorderChecked();
		boolean boldTitleChecked = ebayListingItem.isBoldTitleChecked();
//		boolean setAutoPay = ebayListingItem.isSetAutoPay();
		Integer lotSize = ebayListingItem.getLotSize();
		Boolean bestOfferEnabled = ebayListingItem.getBestOfferEnabled();

		ItemType item = new ItemType();
		item.setTitle(itemTitle);

		// if (subTitle != null && subTitle.length() > 0) {
		// item.setSubTitle(subTitle);
		// }

		item.setDescription(description);

		if (listingDuration != null) {
			item.setListingDuration(listingDuration);
		}

//		item.setRegionID("0");
		item.setLocation("San Jose, CA");
//		item.setCurrency(CurrencyCodeType.USD);

		item.setQuantity(quantity);
		item.setCountry(CountryCodeType.US);

		CategoryType cat = new CategoryType();
		cat.setCategoryID(categoryId);
		item.setPrimaryCategory(cat);

		AmountType at = new AmountType();
		at.setValue(startPrice);
		item.setStartPrice(at);

		if (reservePrice != null) {
			at = new AmountType();
			at.setValue(reservePrice);
			item.setReservePrice(at);
		}

		if (buyItNowPrice != null) {
			at = new AmountType();
			at.setValue(buyItNowPrice);
			item.setBuyItNowPrice(at);
		}

		// ServiceControlManager manager = ServiceControlManager.getInstance();
		// BuyerPaymentMethodCodeType[] arrPaymentMethods = manager
		// .processUserPaymentMethods(this.ckbPaymentServices,
		// this.ctrlPaymentServices);

		BuyerPaymentMethodCodeType[] arrPaymentMethods = { BuyerPaymentMethodCodeType.PAY_PAL };

		if (arrPaymentMethods != null) {
			item.setPaymentMethods(arrPaymentMethods);
		}

		//
		// ShippingDetailsType shippingDetails = manager
		// .processUserShippingDetails(this.shippingServiceOptions,
		// this.intlShippingServiceOptions);
		// if (shippingDetails != null) {
		// item.setShippingDetails(shippingDetails);
		// }
		//

		
		ShippingDetailsType shippingDetails = new ShippingDetailsType();
//		shippingDetails.setShippingType(ShippingTypeCodeType.FLAT);
		
		// At least one valid shipping service needs to be specified
		ShippingServiceOptionsType shippingServiceOption = new ShippingServiceOptionsType();
		shippingServiceOption.setShippingService("USPSMedia");
		at = new AmountType();
		at.setValue(2.50);
		shippingServiceOption.setShippingServiceCost(at);

		ShippingServiceOptionsType[] shippingServiceOptions = { shippingServiceOption };
		shippingDetails.setShippingServiceOptions(shippingServiceOptions);
		item.setShippingDetails(shippingDetails);

		// String[] stl = { "shipToLocation1", "shipToLocation2"};
		// item.setShipToLocations(stl);
		// 

		if (payPalEmail != null && payPalEmail.length() > 0) {
			item.setPayPalEmailAddress(payPalEmail);
		}

		if (vatPercent != null) {
			VATDetailsType vatDetails = new VATDetailsType();
			vatDetails.setVATPercent(vatPercent);
			item.setVATDetails(vatDetails);
		}

		//item.setAutoPay(setAutoPay);

		int cnt = 0;
		if (borderChecked) {
			cnt++;
		}

		if (boldTitleChecked) {
			cnt++;
		}

		if (cnt > 0) {
			ListingEnhancementsCodeType enhancements[] = new ListingEnhancementsCodeType[cnt];
			cnt = 0;
			if (borderChecked) {
				enhancements[cnt++] = ListingEnhancementsCodeType.BORDER;
			}
			if (boldTitleChecked) {
				enhancements[cnt] = ListingEnhancementsCodeType.BOLD_TITLE;
			}
			item.setListingEnhancement(enhancements);
		}

		if (lotSize != null) {
			item.setLotSize(lotSize);
		}

		if (bestOfferEnabled != null) {
			BestOfferDetailsType bo = new BestOfferDetailsType();
			bo.setBestOfferEnabled(bestOfferEnabled);
			item.setBestOfferDetails(bo);
		}

		return item;
	}

	private String[] getPicturePathList() {
		// String[] pathList = { "C:\\Temp//testThumbnail2.jpg" };
		// String[] pathList = { "C:\\Temp\\testThumbnail2.jpg" };
		String[] pathList = { "C:\\Program Files\\PC-Doctor\\Images\\application_icon.png" };
		return pathList;
	}

	class EbayListingItem {

		String itemTitle;
		String subTitle;
		String description;
		String listingDuration;
		Integer quantity;
		String categoryId;
		Double startPrice;
		Double reservePrice;
		Double buyItNowPrice;
		Float vatPercent;
		String payPalEmail;
		boolean borderChecked;
		boolean boldTitleChecked;
		boolean setAutoPay;
		Integer lotSize;
		Boolean bestOfferEnabled;

		public String getItemTitle() {
			return itemTitle;
		}

		public void setItemTitle(String itemTitle) {
			this.itemTitle = itemTitle;
		}

		public String getSubTitle() {
			return subTitle;
		}

		public void setSubTitle(String subTitle) {
			this.subTitle = subTitle;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getListingDuration() {
			return listingDuration;
		}

		public void setListingDuration(String listingDuration) {
			this.listingDuration = listingDuration;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public Double getStartPrice() {
			return startPrice;
		}

		public void setStartPrice(Double startPrice) {
			this.startPrice = startPrice;
		}

		public Double getReservePrice() {
			return reservePrice;
		}

		public void setReservePrice(Double reservePrice) {
			this.reservePrice = reservePrice;
		}

		public Double getBuyItNowPrice() {
			return buyItNowPrice;
		}

		public void setBuyItNowPrice(Double buyItNowPrice) {
			this.buyItNowPrice = buyItNowPrice;
		}

		public Float getVatPercent() {
			return vatPercent;
		}

		public void setVatPercent(Float vatPercent) {
			this.vatPercent = vatPercent;
		}

		public String getPayPalEmail() {
			return payPalEmail;
		}

		public void setPayPalEmail(String payPalEmail) {
			this.payPalEmail = payPalEmail;
		}

		public boolean isBorderChecked() {
			return borderChecked;
		}

		public void setBorderChecked(boolean borderChecked) {
			this.borderChecked = borderChecked;
		}

		public boolean isBoldTitleChecked() {
			return boldTitleChecked;
		}

		public void setBoldTitleChecked(boolean boldTitleChecked) {
			this.boldTitleChecked = boldTitleChecked;
		}

		public boolean isSetAutoPay() {
			return setAutoPay;
		}

		public void setSetAutoPay(boolean setAutoPay) {
			this.setAutoPay = setAutoPay;
		}

		public Integer getLotSize() {
			return lotSize;
		}

		public void setLotSize(Integer lotSize) {
			this.lotSize = lotSize;
		}

		public Boolean getBestOfferEnabled() {
			return bestOfferEnabled;
		}

		public void setBestOfferEnabled(Boolean bestOfferEnabled) {
			this.bestOfferEnabled = bestOfferEnabled;
		}
	}
}
