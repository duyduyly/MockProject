package com.example.demo.constants;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassBatchProgression {
	
	public static class Submit {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Draft", "Rejected", "Declined");
		public static final String NEW_STATUS = "Submitted";
		public static final String COMPLETE_MESSAGE = "Submitted";
	}
	
	public static class Approve {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Submitted");
		public static final String NEW_STATUS = "Planning";
		public static final String COMPLETE_MESSAGE = "Approved";
	}
	
	public static class Accept {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Planning");
		public static final String NEW_STATUS = "Planned";
		public static final String COMPLETE_MESSAGE = "Accepted";
	}
	
	public static class Start {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Planned");
		public static final String NEW_STATUS = "In-progress";
		public static final String COMPLETE_MESSAGE = "Started";
	}

	public static class Finish {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("In-progress", "Waiting for more information");
		public static final String NEW_STATUS = "Pending for review";
		public static final String COMPLETE_MESSAGE = "Finished";
	}
	
	public static class Close {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Pending for review");
		public static final String NEW_STATUS = "Closed";
		public static final String COMPLETE_MESSAGE = "Closed";
	}
	
	public static class Cancel {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Draft", "Submitted");
		public static final String NEW_STATUS = "Cancelled";
		public static final String COMPLETE_MESSAGE = "Cancelled";
	}
	
	public static class Reject {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Submitted");
		public static final String NEW_STATUS = "Rejected";
		public static final String COMPLETE_MESSAGE = "Rejected";
	}
	
	public static class Decline {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Planning");
		public static final String NEW_STATUS = "Declined";
		public static final String COMPLETE_MESSAGE = "Declined";
	}
	
	public static class RequestInfo {
		public static final List<String> REQUIRED_STATUSES = Arrays.asList("Pending for review");
		public static final String NEW_STATUS = "Waiting for more information";
		public static final String COMPLETE_MESSAGE = "Requested";
	}
	
}
