package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CompletionReport;
import com.silanis.esl.sdk.UsageReport;
import com.silanis.esl.sdk.UsageReportCategory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by lena on 2014-05-27.
 */
public class DownloadCompletionAndUsageReportExampleTest {

    @Test
    public void verifyResult() {
        DownloadCompletionAndUsageReportExample example = new DownloadCompletionAndUsageReportExample(Props.get());
        example.run();

        // Assert correct download of completion report
        CompletionReport completionReport = example.sdkCompletionReport;
        assertThat("There should be only 1 sender.", completionReport.getSenders().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of package completion reports should be greater than 1.", completionReport.getSenders().get(0).getPackages().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of document completion report should be greater than 1.", completionReport.getSenders().get(0).getPackages().get(0).getDocuments().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of signer completion report should be greater than 1.", completionReport.getSenders().get(0).getPackages().get(0).getSigners().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Cannot download the completion report in csv format.", example.csvCompletionReport, is(not(isEmptyOrNullString())));

        // Assert correct download of usage report
        UsageReport usageReport = example.sdkUsageReport;
        assertThat("There should be only 1 sender.", usageReport.getSenderUsageReports().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of map entries should be greater or equal to 1.", usageReport.getSenderUsageReports().get(0).getCountByUsageReportCategory().size(), is(greaterThanOrEqualTo(1)));
        assertThat("There should be at a draft key in packages map.", usageReport.getSenderUsageReports().get(0).getCountByUsageReportCategory().containsKey(UsageReportCategory.DRAFT), is(true));
        assertThat("Number of drafts should be greater or equal to 1.", usageReport.getSenderUsageReports().get(0).getCountByUsageReportCategory().get(UsageReportCategory.DRAFT), is(greaterThanOrEqualTo(1)));
        assertThat("Cannot download the usage report in csv format.", example.csvUsageReport, is(not(isEmptyOrNullString())));
    }
}
