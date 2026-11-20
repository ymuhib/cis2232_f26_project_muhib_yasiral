package ca.hccis.task.bo;

import ca.hccis.task.jpa.entity.Task;

    public class TaskBO {

        private static final int HIGH_PRIORITY = 3;
        private static final int MEDIUM_PRIORITY = 2;
        private static final int LOW_PRIORITY = 1;

        /**
         * Calculates the priority score for a task.
         *
         * @param task Task
         * @return Priority score
         */
        public static int calculatePriorityScore(Task task) {

            if (task == null) {
                return 0;
            }

            int priorityValue = 0;

            if ("High".equalsIgnoreCase(task.getPriority())) {
                priorityValue = HIGH_PRIORITY;
            } else if ("Medium".equalsIgnoreCase(task.getPriority())) {
                priorityValue = MEDIUM_PRIORITY;
            } else if ("Low".equalsIgnoreCase(task.getPriority())) {
                priorityValue = LOW_PRIORITY;
            }

            return task.getEstimatedTime() * priorityValue;
        }

    }


