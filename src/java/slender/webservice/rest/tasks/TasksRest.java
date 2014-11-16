/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.tasks;

import javax.ws.rs.core.Response;

/**
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */
public interface TasksRest {
    public Response getTask(Integer id);
    public Response getTaskAttachments(Integer taskId);
    public Response getTaskComments(Integer taskId);
    public Response getTaskUsers(Integer taskId);
    public Response getProgress(Integer taskId);
    public Response getProgressPercentage(Integer taskId);
    public Response addProgress(Integer taskId, Integer userId, int hours);
    public Response markAsComplete(Integer taskId);
    public Response addTaskComment(
            Integer userId,
            Integer projId,
            String comment);
    public Response addTask(
            int projectId, 
            String taskName, 
            String taskDesc, 
            String plannedStartDate,
            String plannedEndDate, 
            int timeAllocation, 
            int priorityId);
}
